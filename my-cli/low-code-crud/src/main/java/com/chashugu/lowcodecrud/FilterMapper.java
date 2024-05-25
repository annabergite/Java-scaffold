package com.chashugu.lowcodecrud;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chashugu.lowcodecrud.model.FilterInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface FilterMapper extends BaseMapper<FilterInfo> {

}