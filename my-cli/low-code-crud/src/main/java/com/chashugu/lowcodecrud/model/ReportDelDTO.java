package com.chashugu.lowcodecrud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author chashugu
 * @Classname: ReportDelDTO
 * @Description:
 * @Date: 2023-08-23 11:00
 */
@ApiModel("删除数据DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDelDTO {
    @ApiModelProperty(value = "表编码", required = true)
    @NotBlank(message = "表编码不能为空")
    private String tableCode;

    @ApiModelProperty(value = "记录的id值", required = true)
    @NotEmpty(message = "id不能为空")
    private List<String> ids;
}
