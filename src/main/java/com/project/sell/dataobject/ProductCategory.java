package com.project.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


/**
 * @Author: HB
 * @Description: 商品类目表实体类
 * @CreateDate: 21:18 2020/10/21
 */

// data-jpa 会将此类和数据表进行映射, 若数据库表与实体类的名字不一致
// 需要使用table注解进行管理. 例如 Table(name = "product_category")
@Entity
// 利用DynamicUpdate注解来进行部分更新操作, 只更新修改的数据
@DynamicUpdate
@Data
public class ProductCategory {

    /* 类目ID */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /* 类目名称 */
    private String categoryName;

    /* 类目类型 */
    private Integer categoryType;

    public ProductCategory() {

    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }


}
