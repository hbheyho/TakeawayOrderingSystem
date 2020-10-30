package com.project.sell.service;

import com.project.sell.dataobject.OrderMaster;
import com.project.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: HB
 * @Description:  Order service 接口类
 * @CreateDate: 19:11 2020/10/27
 */

public interface OrderService {
    // 创建订单
    OrderDTO create(OrderDTO orderDTO);

    // 查询单个订单
    OrderDTO findOne(String orderId);

    // 查询订单列表
    Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);

    // 取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    // 完结订单
    OrderDTO finish(OrderDTO orderDTO);

    // 支付订单
    OrderDTO pay(OrderDTO orderDTO);

}
