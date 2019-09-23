package com.wjh.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.wjh.ssm.domain.SysLog;
import com.wjh.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(Integer page,Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<SysLog> list =  sysLogService.findAll(page,size);
        PageInfo p = new PageInfo(list);

        mv.addObject("sysLogs",p);
        mv.setViewName("syslog-list");
        return mv;
    }
}
