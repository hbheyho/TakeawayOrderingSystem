package com.project.sell.enums;

import lombok.Getter;

/**
 * @Author: HB
 * @Description: 异常枚举类
 * @CreateDate: 23:38 2020/10/26
 */

@Getter
public enum ResultEnum {

    PARAM_ERROR(9, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "库存不足"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13, "订单不存在"),
    ORDER_STATUS_ERROR(14, "订单状态错误"),
    ORDER_UPDATE_ERROR(15, "订单更新失败"),
    ORDER_DETAIL_EMPTY(16, "订单详情为空"),
    PAY_STATUS_ERROR(17, "支付状态错误"),
    CART_EMPTY(18, "购物车为空"),
    ORDER_OWNER_ERROR(19, "该订单不属于当前用户");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
