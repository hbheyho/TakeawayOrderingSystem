package com.project.sell.exception;

import com.project.sell.enums.ResultEnum;

/**
 * @Author: HB
 * @Description: 异常处理类
 * @CreateDate: 20:24 2020/10/27
 */

public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
