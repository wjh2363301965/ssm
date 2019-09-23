package com.wjh.ssm.controller;

import com.wjh.ssm.domain.Permission;
import com.wjh.ssm.domain.Role;
import com.wjh.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> all = roleService.findAll();
        mv.addObject("roleList",all);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }


    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id){
        ModelAndView mv = new ModelAndView();
        //根据id查询role
        Role role = roleService.findRoleById(id);
        //查询这个角色没有的权限
        List<Permission> list = roleService.findAllPermission(id);
        mv.addObject("role",role);
        mv.addObject("permissionList",list);
        mv.setViewName("role-permission-add");
        return mv;

    }

@RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(String roleId,String[] ids){

        ModelAndView mv = new ModelAndView();
        roleService.addPermissionToRole(roleId,ids);

        return "redirect:findAll.do";
}


}
