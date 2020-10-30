package com.project.sell.service;

import com.project.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: HB
 * @Description:  buyer service 接口类
 * @CreateDate: 19:11 2020/10/27
 */

public interface BuyerService {

    // 查询单个订单 - 是否为当前用户的订单
    OrderDTO findOrderOne(String openid, String orderId);


    // 取消订单
    OrderDTO cancelOrder(String openid, String orderId);

   /* // 完结订单
    OrderDTO finish(OrderDTO orderDTO);

    // 支付订单
    OrderDTO pay(OrderDTO orderDTO);*/

}
