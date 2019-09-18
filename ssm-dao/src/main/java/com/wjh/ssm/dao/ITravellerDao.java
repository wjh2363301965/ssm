package com.wjh.ssm.dao;

import com.wjh.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId =#{Id})")
    List<Traveller> findOrdersById(String Id)throws Exception;
}
