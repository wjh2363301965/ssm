package com.wjh.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.wjh.ssm.domain.Orders;
import com.wjh.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private IOrderService orderService;

    //查询全部订单---为分页
   /* @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> all = orderService.findAll();
        mv.addObject("ordersList", all);
        mv.setViewName("orders-list");
        return mv;
    }*/

   @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1") int page,@RequestParam(name="pageSize" ,required = true,defaultValue = "4") int size) throws Exception {
       ModelAndView mv = new ModelAndView();
       List<Orders> all =orderService.findAll(page,size);
       //pageInfo里面包括list（all）但自己的属性里额外多了分页的属性，执行有参构造就会创建出来，添加一个对应的list
       PageInfo pageInfo = new PageInfo(all);
       mv.addObject("pageInfo",pageInfo);
       mv.setViewName("orders-page-list");
       return mv;

   }


}
