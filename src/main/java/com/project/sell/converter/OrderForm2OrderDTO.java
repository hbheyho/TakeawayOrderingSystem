package com.project.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.sell.dataobject.OrderDetail;
import com.project.sell.dataobject.OrderMaster;
import com.project.sell.dto.OrderDTO;
import com.project.sell.enums.ResultEnum;
import com.project.sell.exception.SellException;
import com.project.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: HB
 * @Description: OrderForm => OrderDTO
 * @CreateDate: 16:24 2020/10/30
 */
@Slf4j
public class OrderForm2OrderDTO {

    /**
     * @Author: HB
     * @Description: 将OrderMaster 转换为OrderDTO
     * @Date: 16:26 2020/10/30
     * @Params: null
     * @Returns:
    */
    public static OrderDTO convert(OrderForm orderForm) {

        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            // 将Json字符 => List<OrderDetail>
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("【对象转换失败】错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

}
