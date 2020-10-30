package com.project.sell.enums;

import lombok.Getter;

/**
 * @Author: HB
 * @Description: 订单状态枚举类
 * @CreateDate: 23:35 2020/10/26
 */
@Getter
public enum  OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1,"已完成"),
    CANCEL(2,"已取消");

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
