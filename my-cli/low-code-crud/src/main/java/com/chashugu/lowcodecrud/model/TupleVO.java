package com.chashugu.lowcodecrud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("三元组VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TupleVO {

    @ApiModelProperty("X轴")
    private String key;

    @ApiModelProperty("图例")
    private String item;

    @ApiModelProperty("Y轴")
    private String value;
}