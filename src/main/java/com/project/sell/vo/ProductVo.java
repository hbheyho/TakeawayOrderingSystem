package com.project.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: HB
 * @Description: 商品展示Vo
 * @CreateDate: 15:50 2020/10/24
 */

@Data
public class ProductVo {

    // 通过注解来设置前端展示名称
    // 类目名
    @JsonProperty("name")
    private String categoryName;

    // 类目类型
    @JsonProperty("type")
    private Integer categoryType;

    // 商品列表
    @JsonProperty("foods")
    List<ProductInfoVo> productInfoVoList;
}
