package com.project.sell.vo;

import lombok.Data;

/**
 * @Author: HB
 * @Description: 返回前端展示对象
 * @CreateDate: 15:15 2020/10/24
 */
@Data
public class ResultVo<T> {

    // 状态码
    private Integer code;

    // 提示信息
    private String msg;

    // 数据
    private T data;
}
