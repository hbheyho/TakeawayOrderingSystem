package com.project.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: HB
 * @Description: 商品详情前端展示vo
 * @CreateDate: 15:54 2020/10/24
 */

@Data
public class ProductInfoVo {

    // 商品ID
    @JsonProperty("id")
    private String productId;

    // 商品名
    @JsonProperty("name")
    private String productName;

    // 价格
    @JsonProperty("price")
    private BigDecimal productPrice;

    // 描述
    @JsonProperty("description")
    private String productDescription;

    // 图片
    @JsonProperty("icon")
    private String productIcon;

}
