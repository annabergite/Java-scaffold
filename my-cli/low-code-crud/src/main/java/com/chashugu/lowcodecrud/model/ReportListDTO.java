package com.chashugu.lowcodecrud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author chashugu
 * @Classname: ReportListDTO
 * @Description:
 * @Date: 2023-08-22 15:47
 */
@ApiModel("上报分页列表DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportListDTO {
    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("每页数量")
    private Integer size;

    public Integer getPage() {
        return page == null ? 1 : page;
    }

    public Integer getSize() {
        return size == null ? 10 : size;
    }

    @ApiModelProperty(value = "表编码", required = true)
    @NotBlank(message = "表编码不能为空")
    private String tableCode;

    @ApiModelProperty("筛选项")
    private List<FilterDTO> filter;
}
