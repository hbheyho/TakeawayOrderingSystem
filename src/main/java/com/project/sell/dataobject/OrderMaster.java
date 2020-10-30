package com.project.sell.dataobject;

import com.project.sell.enums.OrderStatusEnum;
import com.project.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: HB
 * @Description: 订单主表实体类
 * @CreateDate: 21:18 2020/10/26
 */

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /** 订单id */
    @Id
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

    /** 订单状态, 默认为0新下单 1 已下单 */
    private Integer orderStatus =  OrderStatusEnum.NEW.getCode();

    /** 支付状态, 默认为0未支付 1 已支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /* 创建时间 */
    private Date createTime;

    /* 更新时间 */
    private Date updateTime;

    /* 当前订单主表下的订单详情表 */
    // Transient 注解会实体类忽略与数据库字段对应
    /*@Transient
    private List<OrderDetail> orderDetailList;*/

}
