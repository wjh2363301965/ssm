package com.wjh.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.wjh.ssm.dao.IOrdersDao;
import com.wjh.ssm.domain.Orders;
import com.wjh.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements IOrderService {

   @Autowired
   private IOrdersDao  ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception{
        //分页语句必须在执行代码之前，如果中间隔了一个行代码就没用了
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }
}
