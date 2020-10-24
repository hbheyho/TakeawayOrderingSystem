package com.project.sell.enums;

import lombok.Getter;

/**
 * @Author: HB
 * @Description: 商品状态枚举类
 * @CreateDate: 14:43 2020/10/24
 */

@Getter
public enum ProductStatusEnum {

    UP(0, "商品在售"), DOWN(1, "商品下架");

    private Integer code;

    private String description;

    ProductStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }


}
