package com.project.sell.repository;

import com.project.sell.dataobject.OrderDetail;
import com.project.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: HB
 * @Description: 订单详情表单元测试
 * @CreateDate: 23:47 2020/10/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest() {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567810");
        orderDetail.setOrderId("11111112");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductId("11111112");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(2.2));
        orderDetail.setProductQuantity(3);

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);

    }


    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("11111112");
        Assert.assertNotEquals(0, orderDetailList.size());
    }
}