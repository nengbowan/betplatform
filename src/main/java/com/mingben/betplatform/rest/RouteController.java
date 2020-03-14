package com.mingben.betplatform.rest;

import com.mingben.betplatform.annotation.AdminAnno;
import com.mingben.betplatform.annotation.UserAnno;
import com.mingben.betplatform.dto.ResultDto;
import com.mingben.betplatform.entity.Admin;
import com.mingben.betplatform.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前端路由控制器
 */
@Controller
public class RouteController {

    @GetMapping("/admin/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        return "login";
    }

    @GetMapping("/admin/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        Admin admin = new Admin();
        admin.setUsername("管理员");
        mmap.put("user", admin);
        return "index";
    }

    /**
     * 管理员信息
     */
    @GetMapping(value = "/admin/info")
    public ResultDto adminInfo(@AdminAnno Admin admin){
        ResultDto result = ResultDto.builder().data(admin).build();
        return result;
    }

    /**
     * 用户信息
     */
    @GetMapping(value = "/user/info")
    public ResultDto adminLogin(@UserAnno User user){
        ResultDto result = ResultDto.builder().data(user).build();
        return result;
    }



}
