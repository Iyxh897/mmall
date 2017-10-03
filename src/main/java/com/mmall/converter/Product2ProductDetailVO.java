package com.mmall.converter;

import com.mmall.pojo.Product;
import com.mmall.util.PropertiesUtil;
import com.mmall.vo.ProductDetailVO;
import org.springframework.beans.BeanUtils;

public class Product2ProductDetailVO {

    public static ProductDetailVO convert(Product product) {
        ProductDetailVO productDetailVO = new ProductDetailVO();
        BeanUtils.copyProperties(product, productDetailVO);
        productDetailVO.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "http://img.happymmall.com/"));
        return productDetailVO;
    }
}
