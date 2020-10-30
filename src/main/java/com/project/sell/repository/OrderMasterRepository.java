package com.project.sell.repository;

import com.project.sell.dataobject.OrderMaster;
import com.project.sell.dataobject.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: HB
 * @Description: OrderMaster 数据库访问接口
 *              注: repository 层就相当于dao层
 * @CreateDate: 21:26 2020/10/26
 */
/* JpaRepository<OrderMaster, Integer> => <实体类, 主键> */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
