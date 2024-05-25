package com.chashugu.lowcodecrud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedHashMap;

/**
 * @author chashugu
 * @Classname: ReportEditDTO
 * @Description:
 * @Date: 2023-08-23 11:12
 */
@ApiModel("修改数据DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportEditDTO {
    @ApiModelProperty(value = "表编码", required = true)
    @NotBlank(message = "表编码不能为空")
    private String tableCode;

    @ApiModelProperty(value = "内容", required = true)
    @NotEmpty(message = "内容不能为空")
    private LinkedHashMap<String, String> info;
}
