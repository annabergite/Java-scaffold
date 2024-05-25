package com.chashugu.lowcodecrud;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chashugu.lowcodecrud.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

//@Repository
@Mapper
public interface MyMapper {

    List<String> getTemplate(@Param("tableCode") String tableCode);

    List<TupleVO> getDimList(@Param("dimClass") String dimClass);

    IPage<LinkedHashMap<String, String>> getData(Page<LinkedHashMap<String, String>> objectPage, @Param("param") ReportListDTO param);

    boolean addOne(@Param("param") ReportAddDTO param);

    void importData(@Param("tableCode") String tableCode, @Param("list") List<List<Object>> list);

    boolean edit(@Param("id") String id, @Param("param") ReportEditDTO param);

    boolean del(@Param("param") ReportDelDTO param);

}
