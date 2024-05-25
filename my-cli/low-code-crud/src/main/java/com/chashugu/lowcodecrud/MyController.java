package com.chashugu.lowcodecrud;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chashugu.lowcodecrud.model.*;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;

@Slf4j
@Api(tags = "首页模块")
@RestController
@Validated
public class MyController {

    @Resource
    private MyService myService;

    @ApiOperationSupport(order = 1)
    @GetMapping("/getHeader")
    @ApiOperation("获取表头及筛选条件")
    public Result<HeaderVO> getHeader(@NotBlank(message = "表编码不能为空")
                                      @RequestParam("tableCode") String tableCode) {
        return Result.success(myService.getHeader(tableCode));
    }

    @ApiOperationSupport(order = 2)
    @PostMapping("/getData")
    @ApiOperation("分页获取表数据")
    public Result<IPage<LinkedHashMap<String, String>>> getData(@Valid @RequestBody ReportListDTO param) {
        return Result.success(myService.getData(param));
    }

    @ApiOperationSupport(order = 3)
    @PostMapping("/addOne")
    public Result<?> addOne(@Valid @RequestBody ReportAddDTO param) {
        return myService.addOne(param) ? Result.success(null, "操作成功") : Result.fail("操作失败");
    }

    @ApiOperation("修改一条数据")
    @ApiOperationSupport(order = 4)
    @PostMapping("/edit")
    public Result<?> edit(@Valid @RequestBody ReportEditDTO param) {
        return myService.edit(param) ? Result.success(null, "操作成功") : Result.fail("操作失败");
    }

    @ApiOperation("删除记录(支持批量)")
    @ApiOperationSupport(order = 5)
    @PostMapping("/del")
    public Result<?> del(@Valid @RequestBody ReportDelDTO param) {
        return myService.del(param) ? Result.success(null, "操作成功") : Result.fail("操作失败");
    }

    @ApiOperation("下载导入模板")
    @ApiOperationSupport(order = 6)
    @GetMapping("/getTemplate")
    public void getTemplate(@ApiParam(value = "表编码", required = true)
                            @NotBlank(message = "表编码不能为空")
                            @RequestParam("tableCode") String tableCode, HttpServletResponse response) {
        myService.getTemplate(tableCode, response);
    }

    @ApiOperation("数据导入")
    @ApiOperationSupport(order = 7)
    @PostMapping("/importData")
    public Result<?> importData(@ApiParam(value = "表编码", required = true)
                                @NotBlank(message = "表编码不能为空")
                                @RequestParam("tableCode") String tableCode,
                                @ApiParam(value = "上传的文件", required = true)
                                @NotNull(message = "上传文件不能为空") MultipartFile file) {
        return myService.importData(tableCode, file) ? Result.success(null, "导入成功") : Result.fail("导入失败");
    }
}
