package com.mmall.converter;

import com.mmall.pojo.Product;
import com.mmall.util.PropertiesUtil;
import com.mmall.vo.ProductListVO;
import org.springframework.beans.BeanUtils;

public class Product2ProductListVO {

    public static ProductListVO convert(Product product) {
        ProductListVO productListVO = new ProductListVO();
        BeanUtils.copyProperties(product, productListVO);
        productListVO.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "http://img.happymmall.com/"));
        return productListVO;
    }
}
