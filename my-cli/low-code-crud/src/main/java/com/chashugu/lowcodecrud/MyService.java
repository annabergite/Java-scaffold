package com.chashugu.lowcodecrud;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chashugu.lowcodecrud.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Service
public class MyService {

    private static final String ONLY_KEY = "only_key";

    @Resource
    private MyMapper dao;

    @Resource
    private FilterServiceImpl filterInfoService;

    public void getTemplate(String tableCode, HttpServletResponse response) {
        try (ExcelWriter writer = ExcelUtil.getWriter(true)) {
            response.setCharacterEncoding("UTF-8");
            List<List<String>> data = new ArrayList<>();
            List<String> result = dao.getTemplate(tableCode);
            data.add(result);
            // 一次性写出内容，使用默认样式，强制输出标题
            writer.write(data, true);
            ServletOutputStream out = null;
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(tableCode, "UTF-8") + ".xlsx");
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HeaderVO getHeader(String tableCode) {
        HeaderVO result = new HeaderVO();
        List<FilterInfo> headers = filterInfoService.list(new LambdaQueryWrapper<FilterInfo>()
                .eq(FilterInfo::getTableCode, tableCode)
                .orderByAsc(FilterInfo::getSort)
        );
        List<TupleVO> headList = new ArrayList<>();
        List<FilterVO> filterList = new ArrayList<>();
        for (FilterInfo header : headers) {
            //存放表头信息
            headList.add(new TupleVO(header.getColumnCode(), header.getIsRequired(), header.getColumnName()));
            //获取特殊组件字典信息
            List<TupleVO> dimList = new ArrayList<>();
            if (header.getType().equals("select")) {
                dimList = dao.getDimList(header.getDimClass());
            }
            filterList.add(FilterVO.builder()
                    .columnCode(header.getColumnCode())
                    .columnName(header.getColumnName())
                    .type(header.getType())
                    .isRequired(header.getIsRequired())
                    .info(dimList)
                    .build());
        }
        result.setHeader(headList);
        result.setFilter(filterList);
        return result;
    }

    public IPage<LinkedHashMap<String, String>> getData(ReportListDTO param) {
        return dao.getData(new Page<>(param.getPage(), param.getSize()), param);
    }

    public boolean addOne(ReportAddDTO param) {
        Assert.isTrue(!param.getInfo().isEmpty(), "参数列表不能为空");
        Assert.isTrue(param.getInfo().containsKey(ONLY_KEY), "主键不存在");
        param.getInfo().put(ONLY_KEY, IdUtil.simpleUUID());
        return dao.addOne(param);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean edit(ReportEditDTO param) {
        Assert.isTrue(!param.getInfo().isEmpty(), "参数列表不能为空");
        Assert.isTrue(param.getInfo().containsKey(ONLY_KEY), "主键不存在");
        String id = param.getInfo().get(ONLY_KEY);
        param.getInfo().remove(ONLY_KEY);
        return dao.edit(id, param);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean del(ReportDelDTO param) {
        return dao.del(param);
    }


    public boolean importData(String tableCode, MultipartFile file) {
        try {
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
            List<List<Object>> list = reader.read();

            int index = -1;
            for (int k = 0; k < list.get(0).size(); k++) {
                if (ONLY_KEY.equals(list.get(0).get(k))) {
                    index = k;
                    break;
                }
            }
            Assert.isTrue(index >= 0, "缺少主键");
            //去掉标题行
            list.remove(0);
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    if (j == index) {
                        list.get(i).set(j, IdUtil.simpleUUID());
                        break;
                    }
                }
            }
            dao.importData(tableCode, list);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }
}
