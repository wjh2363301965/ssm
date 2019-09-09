package com.wjh.ssm.dao;

import com.wjh.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

public interface IProductDao {

@Select("select * from product")
    public List<Product> findAll() throws  Exception;

    @Insert("insert into product values (#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},# {productDesc},#{productStatus})")
    void save(Product product);

    @Select("select * from product where id=#{id}")
    public Product findById(String id);

}
