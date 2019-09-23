package com.wjh.ssm.controller;

import com.wjh.ssm.domain.Permission;
import com.wjh.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/permission")
@Controller
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> list = permissionService.findAll();
        mv.addObject("permissionList", list);
        mv.setViewName("permission-list");
        return mv;

    }

    @RequestMapping("/save.do")
    public String save(Permission p) throws Exception{
        permissionService.save(p);
        return "redirect:findAll.do";
    }

}
