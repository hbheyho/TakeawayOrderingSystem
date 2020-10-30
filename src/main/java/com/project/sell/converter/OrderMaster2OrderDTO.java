package com.project.sell.converter;

import com.project.sell.dataobject.OrderMaster;
import com.project.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: HB
 * @Description: OrderMaster => OrderDTO
 * @CreateDate: 16:24 2020/10/30
 */

public class OrderMaster2OrderDTO {

    /**
     * @Author: HB
     * @Description: 将OrderMaster 转换为OrderDTO
     * @Date: 16:26 2020/10/30
     * @Params: null
     * @Returns:
    */
    public static OrderDTO convert(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;

    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        // 将List<OrderMaster> 转换为 List<OrderDTO>
        List<OrderDTO> orderDTOList = new ArrayList<>();
        /* 方法一
        for (OrderMaster orderMaster : orderMasterList) {
            orderDTOList.add(OrderMaster2OrderDTO.convert(orderMaster));
        }*/

        // 方法二
        orderDTOList = orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
        return orderDTOList;
    }
}
