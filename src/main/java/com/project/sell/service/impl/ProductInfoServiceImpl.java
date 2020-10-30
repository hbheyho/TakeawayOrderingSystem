package com.project.sell.service.impl;

import com.project.sell.dataobject.ProductInfo;
import com.project.sell.dto.CartDTO;
import com.project.sell.enums.ProductStatusEnum;
import com.project.sell.enums.ResultEnum;
import com.project.sell.exception.SellException;
import com.project.sell.repository.ProductInfoRepository;
import com.project.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: HB
 * @Description: ProductInfo service 接口实现类
 * @CreateDate: 14:40 2020/10/24
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**
     * @Author: HB
     * @Description: 根据商品ID查询商品
     * @Date: 14:41 2020/10/24
     * @Params: null
     * @Returns: 
    */
    @Override
    public ProductInfo findOne(String productId) {

        return productInfoRepository.findOne(productId);

    }

    /**
     * @Author: HB
     * @Description: 查询所有在售商品
     * @Date: 14:42 2020/10/24
     * @Params: null
     * @Returns:
    */
    @Override
    public List<ProductInfo> findUpAll() {

        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());

    }


    /**
     * @Author: HB
     * @Description: 分页查询所有商品
     * @Date: 14:50 2020/10/24
     * @Params: null
     * @Returns:
    */
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {

        return productInfoRepository.findAll(pageable);

    }


    /**
     * @Author: HB
     * @Description: 新增商品
     * @Date: 14:50 2020/10/24
     * @Params: null
     * @Returns:
    */
    @Override
    public ProductInfo save(ProductInfo productInfo) {

        return productInfoRepository.save(productInfo);

    }

    /**
     * @Author: HB
     * @Description: 增加商品库存
     * @Date: 21:07 2020/10/27
     * @Params: null
     * @Returns:
    */
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList) {

            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if (productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);

            // 计算库存容量
            int result = productInfo.getProductStock() + cartDTO.getProductQuantity();

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);

        }
    }


    /**
     * @Author: HB
     * @Description: 修改商品库存
     * @Date: 21:07 2020/10/27
     * @Params: null
     * @Returns:
    */
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList) {

            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if (productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);

            // 计算库存容量
            int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);

        }

    }
}
