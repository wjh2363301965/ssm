package com.wjh.ssm.controller;

import com.wjh.ssm.domain.UserInfo;
import com.wjh.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> list =  userService.findAll();
        mv.addObject("userList",list);
        mv.setViewName("user-list");
        return mv;

    }

    //用户添加
    @RequestMapping("save.do")
    public String save(UserInfo userInfo) throws  Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

@RequestMapping("findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        UserInfo u  = userService.findById(id);
        mv.addObject("user",u);
        mv.setViewName("user-show1");
        return mv;

}


}
