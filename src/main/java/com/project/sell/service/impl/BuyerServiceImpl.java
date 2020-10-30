package com.project.sell.service.impl;

import com.project.sell.dto.OrderDTO;
import com.project.sell.enums.ResultEnum;
import com.project.sell.exception.SellException;
import com.project.sell.service.BuyerService;
import com.project.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: HB
 * @Description: 用户service实现类
 * @CreateDate: 23:39 2020/10/30
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    /**
     * @Author: HB
     * @Description: 查询是否是当前用户订单
     * @Date: 23:41 2020/10/30
     * @Params: null
     * @Returns:
    */
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    /**
     * @Author: HB
     * @Description: 查询是否是当前用户订单
     * @Date: 23:41 2020/10/30
     * @Params: null
     * @Returns:
    */
    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO =  checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】错误, 查不到该订单, orderId: {}", orderId);
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    /**
     * @Author: HB
     * @Description: 检查订单所属是否一致
     * @Date: 23:46 2020/10/30
     * @Params: null
     * @Returns:
    */
    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null)
            return null;
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】错误, openid不一致, openId: {}", openid);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
