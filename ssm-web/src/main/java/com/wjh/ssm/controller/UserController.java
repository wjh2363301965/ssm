package com.wjh.ssm.controller;

import com.wjh.ssm.domain.Role;
import com.wjh.ssm.domain.UserInfo;
import com.wjh.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> list = userService.findAll();
        mv.addObject("userList", list);
        mv.setViewName("user-list");
        return mv;

    }

    //用户添加
    @RequestMapping("save.do")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("findById.do")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        UserInfo u = userService.findById(id);
        mv.addObject("user", u);
        mv.setViewName("user-show1");
        return mv;


    }

    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String Id) {
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        UserInfo userInfo = userService.findById(Id);

        //2.根据用户id查询可以添加的角色
        List<Role> list = userService.findOtherRoles(Id);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", list);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleId) {
        userService.addRoleToUser(userId, roleId);
        return "redirect:findAll.do";
    }

}
