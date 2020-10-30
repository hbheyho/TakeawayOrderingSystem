package com.project.sell.repository;

import com.project.sell.dataobject.OrderDetail;
import com.project.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: HB
 * @Description: OrderDetail 数据库访问接口
 *              注: repository 层就相当于dao层
 * @CreateDate: 21:26 2020/10/26
 */
/* JpaRepository<OrderDetail, Integer> => <实体类, 主键> */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);

}
