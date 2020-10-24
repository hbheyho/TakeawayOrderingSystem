package com.project.sell.repository;

import com.project.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: HB
 * @Description: ProductInfo 数据库访问接口
 *              注: repository 层就相当于dao层
 * @CreateDate: 21:26 2020/10/21
 */
/* JpaRepository<ProductInfo, Integer> => <实体类, 主键> */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

}
