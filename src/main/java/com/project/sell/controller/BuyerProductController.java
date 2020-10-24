package com.project.sell.controller;

import com.project.sell.dataobject.ProductCategory;
import com.project.sell.dataobject.ProductInfo;
import com.project.sell.service.ProductCategoryService;
import com.project.sell.service.ProductInfoService;
import com.project.sell.utils.ResultVoUtil;
import com.project.sell.vo.ProductInfoVo;
import com.project.sell.vo.ProductVo;
import com.project.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author: HB
 * @Description: 买家商品 controller类
 * @CreateDate: 15:09 2020/10/24
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVo list() {

        // 1. 查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        // 2. 查询类目 - 根据类目类别
        // (1) 传统做法
        /* List<Integer> categoryTypeList = Arrays.asList();
         for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }*/

        // (2) lambda 表达式
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        // 数据拼装
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            // 遍历商品列表
            for (ProductInfo productInfo : productInfoList) {
                // 相同类目的商品放在一起
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }

            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }

        // 4. 返回结果
        return ResultVoUtil.success(productVoList);
    }
}
