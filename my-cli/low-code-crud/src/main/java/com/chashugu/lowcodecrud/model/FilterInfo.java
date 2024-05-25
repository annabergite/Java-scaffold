package com.chashugu.lowcodecrud.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("filter_info")
@AllArgsConstructor
public class FilterInfo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id")
    protected Long id;

    /**
     * 表编码
     */
    private String tableCode;

    /**
     * 字段码
     */
    private String columnCode;

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 类型
     */
    private String type;

    /**
     * 对应维度值
     */
    private String dimClass;

    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否必须
     */
    private String isRequired;


}