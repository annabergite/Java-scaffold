package com.chashugu.lowcodecrud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.List;

@ApiModel("表头及筛选条件VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("HeaderVO")
public class HeaderVO {
    @ApiModelProperty("表头")
    private List<TupleVO> header;
    @ApiModelProperty("筛选条件")
    private List<FilterVO> filter;
}
