package com.mingben.betplatform.rest.backend;

import com.mingben.betplatform.dto.ResultDto;
import com.mingben.betplatform.entity.User;
import com.mingben.betplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 用户管理
 */
@RestController(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户
     * @param username
     */
    @GetMapping
    public ResultDto userQuery(@PageableDefault Pageable pageable , String username) {
        Page<List<User>> users = userService.findByUsernameLike(pageable , "%"+username+"%");
        return ResultDto.builder().data(users).build();
    }

    /**
     * 创建用户
     * @param username
     * @param passwd
     * @param userType
     */
    @PostMapping(value = "/")
    public ResultDto userCreate(String username, String passwd , String userType){
        userService.userCreate(username , passwd , userType);
        return ResultDto.builder().build();
    }

    /**
     * 修改用户
     * @param username
     * @param passwd
     * @param userType
     */
    @PutMapping(value = "/")
    public ResultDto userModify(String username, String passwd , String userType){
        userService.userModify(username , passwd , userType);
        return ResultDto.builder().build();
    }



}
