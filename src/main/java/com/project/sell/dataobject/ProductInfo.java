package com.project.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author: HB
 * @Description: 商品信息实体表
 * @CreateDate: 14:20 2020/10/24
 */
@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;

    /* 商品名称 */
    private String productName;

    /* 商品价格 */
    private BigDecimal productPrice;

    /* 商品库存 */
    private Integer productStock;

    /* 商品描述 */
    private String productDescription;

    /* 商品小涂 */
    private String productIcon;

    /* 商品状态 0 - 正常 1 - 下架 */
    private Integer productStatus;

    /* 类目编号 */
    private Integer categoryType;
}
