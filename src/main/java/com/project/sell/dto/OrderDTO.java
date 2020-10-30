package com.project.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.sell.dataobject.OrderDetail;
import com.project.sell.utils.serializer.DateToLongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: HB
 * @Description: 订单数据传输对象
 * @CreateDate: 19:33 2020/10/27
 */

@Data
// JSON 反序列化的排除为null的字段 - v2.0 已被废弃
/*@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)*/
/* 局部配置
@JsonInclude(JsonInclude.Include.NON_NULL)*/
public class OrderDTO {

    /** 订单id */
    private String orderId;

    /** 买家名字 */
    private String buyerName;

    /** 买家手机号 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信Openid */
    private String buyerOpenid;

    /** 订单总金额 */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单 */
    private Integer orderStatus;

    /** 支付状态, 默认为0未支付 */
    private Integer payStatus;

    /* 创建时间 */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    /** 更新时间 */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    /* 当前订单主表下的订单详情表 */
    private List<OrderDetail> orderDetailList;
}
