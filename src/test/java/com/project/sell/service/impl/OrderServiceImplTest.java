package com.project.sell.service.impl;

import com.project.sell.dataobject.OrderDetail;
import com.project.sell.dto.OrderDTO;
import com.project.sell.enums.OrderStatusEnum;
import com.project.sell.enums.PayStatusEnum;
import com.project.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: HB
 * @Description: 订单相关测试类
 * @CreateDate: 21:15 2020/10/27
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("西南交通大学");
        orderDTO.setBuyerName("HB");
        orderDTO.setBuyerPhone("18770640829");
        orderDTO.setBuyerOpenid("10101001");

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("121212");
        orderDetail.setProductQuantity(10);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("121216");
        orderDetail1.setProductQuantity(10);

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(orderDetail);
        orderDetails.add(orderDetail1);

        orderDTO.setOrderDetailList(orderDetails);

        OrderDTO result = orderService.create(orderDTO);

        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {

        String orderID = "1603806884531634255";
        OrderDTO orderDTO = orderService.findOne(orderID);
        log.info("{}", orderDTO);

    }

    @Test
    public void findList() {

        String buyerOpenID = "10101001";
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOList = orderService.findList(buyerOpenID, pageRequest);
        Assert.assertNotEquals(0, orderDTOList.getTotalElements());
        log.info("{}", orderDTOList.getContent());

    }

    @Test
    public void cancel() {

        String orderID = "1603806884531634255";
        OrderDTO orderDTO = orderService.findOne(orderID);
        OrderDTO orderDTOResult = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), orderDTOResult.getOrderStatus());

    }

    @Test
    public void finish() {

        String orderID = "1603806884531634255";
        OrderDTO orderDTO = orderService.findOne(orderID);
        OrderDTO orderDTOResult = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), orderDTOResult.getOrderStatus());

    }

    @Test
    public void pay() {

        String orderID = "1603806884531634255";
        OrderDTO orderDTO = orderService.findOne(orderID);
        OrderDTO orderDTOResult = orderService.pay(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), orderDTOResult.getPayStatus());

    }
}