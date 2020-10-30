package com.project.sell.service.impl;

import com.project.sell.converter.OrderMaster2OrderDTO;
import com.project.sell.dataobject.OrderDetail;
import com.project.sell.dataobject.OrderMaster;
import com.project.sell.dataobject.ProductInfo;
import com.project.sell.dto.CartDTO;
import com.project.sell.dto.OrderDTO;
import com.project.sell.enums.OrderStatusEnum;
import com.project.sell.enums.PayStatusEnum;
import com.project.sell.enums.ResultEnum;
import com.project.sell.exception.SellException;
import com.project.sell.repository.OrderDetailRepository;
import com.project.sell.repository.OrderMasterRepository;
import com.project.sell.service.OrderService;
import com.project.sell.service.ProductInfoService;
import com.project.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: HB
 * @Description: Order Service 实现类
 * @CreateDate: 19:37 2020/10/27
 */

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    /**
     * @Author: HB
     * @Description: 创建订单
     * @Date: 19:38 2020/10/27
     * @Params: orderDTO
     * @Returns:
    */
    @Override
    // 添加@Transactional, 操作失败会进行回滚
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        // 生成OrderId
        String orderId = KeyUtil.generateUniqueKey();

        // 订单总价
        BigDecimal orderAmount = new BigDecimal("0");

        // 1.查询商品(库存, 价格)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {

            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);

            //2. 计算订单总价
            orderAmount = orderAmount.add(productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())));

            //3. 订单详情入库
            // 属性拷贝 - 注意null值的拷贝
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setDetailId(KeyUtil.generateUniqueKey());
            orderDetail.setOrderId(orderId);

            orderDetailRepository.save(orderDetail);

        }
        // 4. 写入数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        orderDTO.setCreateTime(new Date());
        orderDTO.setUpdateTime(new Date());
        // 属性拷贝 - 注意null值的拷贝
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);

        // 4. 修改库存 - 一次性对库存进行修改
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    /**
     * @Author: HB
     * @Description: 查询订单 - 单个
     * @Date: 19:38 2020/10/27
     * @Params: null
     * @Returns:
    */
    @Override
    public OrderDTO findOne(String orderId) {
        // 查询订单主表
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null)
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);

        // 查询订单详情列表
        // 查询当前主订单下的详细订单
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList))
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);

        OrderDTO orderDTO = new OrderDTO();
        // 属性拷贝
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;

    }

    /**
     * @Author: HB
     * @Description: 查询订单 - 用户全部订单
     * @Date: 19:38 2020/10/27
     * @Params: null
     * @Returns:
    */
    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        // 找到当前用户所有订单列表
        Page<OrderMaster> orderMasterList = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
        // 将List<OrderMaster> 转换为 List<OrderDTO>
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.convert(orderMasterList.getContent());
        // 重新构建Page对象
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterList.getTotalElements());
        return orderDTOPage;
    }

    /**
     * @Author: HB
     * @Description: 取消订单
     * @Date: 19:39 2020/10/27
     * @Params: null
     * @Returns:
    */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();

        //1. 判断订单状态
        // 用户已下单或已经取消, 则操作失败
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】失败, 订单状态不正确, orderID：{}, orderStatus: {}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2. 修改订单状态 - 设置为取消状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【取消订单】失败, 状态更新失败, orderID：{}",
                    updateResult.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }

        //3. 返回库存
        // 订单中无商品
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】失败, 更新库存失败, orderID: {}",
                    orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        // 进行库存返回操作
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        //4. 如果已经支付, 退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            // TODO 退款操作
        }

        return orderDTO;

    }

    /**
     * @Author: HB
     * @Description: 完成订单
     * @Date: 19:39 2020/10/27
     * @Params: null
     * @Returns:
    */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        //1. 判断订单状态
        // 用户已完成或已经取消, 则操作失败
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】失败, 订单状态不正确, orderID：{}, orderStatus: {}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2. 修改订单状态 - 设置为已完成状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完成订单】失败, 状态更新失败, orderID：{}",
                    updateResult.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }

        return orderDTO;
    }

    /**
     * @Author: HB
     * @Description: 支付订单
     * @Date: 19:39 2020/10/27
     * @Params: null
     * @Returns:
    */
    @Override
    @Transactional
    public OrderDTO pay(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        //1. 判断订单状态
        // 用户已完成或已经取消, 则操作失败
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单】失败, 订单状态不正确, orderID：{}, orderStatus: {}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2. 判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【支付订单】失败, 支付状态不正确, orderID：{}, orderStatus: {}",
                    orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }


        //2. 修改支付状态 - 设置为已完成状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【支付订单】失败, 支付状态更新失败, orderID：{}",
                    updateResult.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }

        return orderDTO;
    }

    
}
