package com.project.sell.controller;

import com.project.sell.converter.OrderForm2OrderDTO;
import com.project.sell.dataobject.ProductCategory;
import com.project.sell.dataobject.ProductInfo;
import com.project.sell.dto.OrderDTO;
import com.project.sell.enums.ResultEnum;
import com.project.sell.exception.SellException;
import com.project.sell.form.OrderForm;
import com.project.sell.service.BuyerService;
import com.project.sell.service.OrderService;
import com.project.sell.service.ProductCategoryService;
import com.project.sell.service.ProductInfoService;
import com.project.sell.utils.ResultVoUtil;
import com.project.sell.vo.ProductInfoVo;
import com.project.sell.vo.ProductVo;
import com.project.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: HB
 * @Description: 买家订单 controller类
 * @CreateDate: 15:09 2020/10/24
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * @Author: HB
     * @Description: 创建订单
     * @Date: 21:45 2020/10/30
     * @Params: null
     * @Returns: 
    */
    @PostMapping("/create")
    /* valid: 表单验证 */
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderFrom={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        // OrderForm => OrderDTO
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单错误, 购物车为空】");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVoUtil.success(map);
    }

    /**
     * @Author: HB
     * @Description:  订单列表
     * @Date: 22:50 2020/10/30
     * @Params: null
     * @Returns:
    */
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】错误, openid 为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);

        return ResultVoUtil.success(orderDTOPage.getContent());

    }


    /**
     * @Author: HB
     * @Description:  订单详情
     * @Date: 23:50 2020/10/30
     * @Params: null
     * @Returns:
     */
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtil.success(orderDTO);
    }
    
    
    /**
     * @Author: HB
     * @Description: 取消订单
     * @Date: 23:31 2020/10/30
     * @Params: null
     * @Returns: 
    */
    @GetMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultVoUtil.success();
    }

    /**
     * @Author: HB
     * @Description: todo 完成订单
     * @Date: 23:55 2020/10/30
     * @Params: null
     * @Returns:
    */
    /*OrderDTO finish(OrderDTO orderDTO)*/

    // todo 支付订单
    /*OrderDTO pay(OrderDTO orderDTO)*/

    
}
