package com.wjh.ssm.controller;

import com.wjh.ssm.domain.Product;
import com.wjh.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {


    //查询全部产品
    @Autowired
    private IProductService iProductService;
    @RequestMapping("findAll.do")
    public ModelAndView findALl() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = iProductService.findAll();
        for (Product p : ps) {
            System.out.println(p);
        }
        mv.addObject("productList",ps);
        mv.setViewName("product-list1");
        return mv;

    }

@RequestMapping("/save.do")
    public  String save(Product product){
        iProductService.save(product);
        return "redirect:findAll.do";

    }
}
