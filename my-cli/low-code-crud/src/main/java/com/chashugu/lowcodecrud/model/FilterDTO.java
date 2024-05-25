package com.chashugu.lowcodecrud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("筛选条件DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterDTO {
    @ApiModelProperty("字段code")
    private String columnCode;
    @ApiModelProperty("值")
    private String value;
    @ApiModelProperty("类型-INPUT:输入框;TIME:时间选择器;TIMEAREA:时间段选择器;SELECT:下拉选择;NUMBER:数字")
    private String type;
    @ApiModelProperty("起始时间(时间、时间段专用)")
    private String startTime;
    @ApiModelProperty("结束时间(时间段专用)")
    private String endTime;
}
