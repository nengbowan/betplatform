package com.mingben.betplatform.rest.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingben.betplatform.annotation.AdminAnno;
import com.mingben.betplatform.dto.ResultDto;
import com.mingben.betplatform.entity.Admin;
import com.mingben.betplatform.exception.UserNotExistException;
import com.mingben.betplatform.exception.UserPasswordNotMatchException;
import com.mingben.betplatform.service.AdminService;

/**
 * 管理员管理
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        return "login";
    }
    
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
    	// 取身份信息
    	Admin admin = new Admin();
    	admin.setUsername("管理员");
        mmap.put("user", admin);
        return "index";
    }
    /**
     * 管理员登录
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResultDto adminLogin(@RequestBody Admin admin){
        ResultDto result = ResultDto.builder().build();
        try {
            String jwtToken = adminService.adminLogin(admin.getUsername() , admin.getPasswd());
            result.setData(jwtToken);
        } catch (UserNotExistException e) {
            result.setSuccess(Boolean.FALSE);
            result.setMessage(e.getMessage());
        } catch (UserPasswordNotMatchException e) {
            result.setSuccess(Boolean.FALSE);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 管理员信息
     */
    @GetMapping(value = "/info")
    public ResultDto adminInfo(@AdminAnno Admin admin){
        ResultDto result = ResultDto.builder().data(admin).build();
        return result;
    }

}
