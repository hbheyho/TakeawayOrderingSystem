package com.project.sell.repository;

import com.project.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.security.krb5.internal.PAData;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author: HB
 * @Description: 订单主表单元测试
 * @CreateDate: 23:47 2020/10/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void saveTest() {

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1232232");
        orderMaster.setBuyerName("QQ");
        orderMaster.setBuyerAddress("北京");
        orderMaster.setBuyerPhone("110");
        orderMaster.setBuyerOpenid("1111");
        orderMaster.setOrderAmount(new BigDecimal("222.2"));
//        orderMaster.setCreateTime(new Date());
//        orderMaster.setUpdateTime(new Date());
        OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);

        Assert.assertNotNull(orderMaster1);
    }


    @Test
    public void findByBuyerOpenid() {
        String openId = "1111";
        PageRequest pageRequest = new PageRequest(0, 3);
        Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenid(openId, pageRequest);
        System.out.println(page.getTotalElements());
        Assert.assertNotEquals(0, page.getTotalElements());

    }
}