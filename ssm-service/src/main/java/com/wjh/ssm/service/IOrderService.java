package com.wjh.ssm.service;

import com.wjh.ssm.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IOrderService {


    List<Orders> findAll( int page,int size) throws Exception;
}
