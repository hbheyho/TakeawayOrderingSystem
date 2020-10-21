package com.project.sell.repository;

import com.project.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: HB
 * @Description: ProductCategory 数据库访问接口
 *              注: repository 层就相当于dao层
 * @CreateDate: 21:26 2020/10/21
 */
/* JpaRepository<ProductCategory, Integer> => <实体类, 主键> */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
