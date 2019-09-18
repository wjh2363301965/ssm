package com.wjh.ssm.dao;

import com.wjh.ssm.domain.Member;
import com.wjh.ssm.domain.Orders;
import com.wjh.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true ,property = "id",column = "id"),
            @Result(property="orderNum",column = "orderNum"),
            @Result(property="orderTime",column = "orderTime"),
            @Result(property="orderStatus",column = "orderStatus"),
            @Result(property="peopleCount",column = "peopleCount"),
            @Result(property="payType",column = "payType"),
            @Result(property="orderDesc",column = "orderDesc"),
            @Result(property="product",column = "productId",javaType = Product.class,one = @One(select="com.wjh.ssm.dao.IProductDao.findById"))
    })
    List<Orders> findAll();

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true ,property = "id",column = "id"),
            @Result(property="orderNum",column = "orderNum"),
            @Result(property="orderTime",column = "orderTime"),
            @Result(property="orderStatus",column = "orderStatus"),
            @Result(property="peopleCount",column = "peopleCount"),
            @Result(property="payType",column = "payType"),
            @Result(property="orderDesc",column = "orderDesc"),
            @Result(property="product",column = "productId",javaType = Product.class,one = @One(select="com.wjh.ssm.dao.IProductDao.findById")),
            @Result(property="member",column = "memberId",javaType = Member.class,one = @One(select="com.wjh.ssm.dao.IMemberDao.findById")),
            @Result(property="travellers",column = "id",javaType = java.util.List.class,many = @Many(select="com.wjh.ssm.dao.ITravellerDao.findOrdersById")),
    })
    Orders findById(String id);
}
