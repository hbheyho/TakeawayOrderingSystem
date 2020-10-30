package com.project.sell.enums;

import lombok.Getter;

/**
 * @Author: HB
 * @Description: 支付状态枚举类
 * @CreateDate: 23:38 2020/10/26
 */

@Getter
public enum  PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1,"支付成功");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
