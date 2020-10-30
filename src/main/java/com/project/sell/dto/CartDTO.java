package com.project.sell.dto;

import lombok.Data;


/**
 * @Author: HB
 * @Description: 购物车数据传输对象
 * @CreateDate: 19:33 2020/10/27
 */

@Data
public class CartDTO {

    /** 商品id */
    private String productId;

    /** 商品库存 */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
