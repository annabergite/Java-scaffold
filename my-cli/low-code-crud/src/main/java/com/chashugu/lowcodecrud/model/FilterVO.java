package com.chashugu.lowcodecrud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("筛选条件VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterVO {
    @ApiModelProperty("字段code")
    private String columnCode;
    @ApiModelProperty("字段名称")
    private String columnName;
    @ApiModelProperty("类型-INPUT:输入框;DATE:日期选择器;DATERANGE:日期段;DATETIME：时间选择器;DATETIMERANGE:时间段;SELECT:下拉选择;NUMBER:数字")
    private String type;
    @ApiModelProperty("下拉字典")
    private List<TupleVO> info;
    @ApiModelProperty("是否必须")
    private String isRequired;
}
