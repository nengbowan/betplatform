package com.mingben.betplatform.rest.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.mingben.betplatform.annotation.AdminAnno;
import com.mingben.betplatform.dto.ResultDto;
import com.mingben.betplatform.entity.Admin;
import com.mingben.betplatform.exception.UserNotExistException;
import com.mingben.betplatform.exception.UserPasswordNotMatchException;
import com.mingben.betplatform.service.AdminService;

/**
 * 管理员管理
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    

    /**
     * 管理员登录
     */
    @PostMapping(value = "/login")
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


}
