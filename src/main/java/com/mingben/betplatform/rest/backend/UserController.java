package com.mingben.betplatform.rest.backend;

import com.mingben.betplatform.annotation.AdminAnno;
import com.mingben.betplatform.annotation.UserAnno;
import com.mingben.betplatform.dto.ResultDto;
import com.mingben.betplatform.entity.Admin;
import com.mingben.betplatform.entity.User;
import com.mingben.betplatform.exception.UserNotExistException;
import com.mingben.betplatform.exception.UserPasswordNotMatchException;
import com.mingben.betplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
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
        Page<List<User>> users = userService.userQuery(pageable , username);
        return ResultDto.builder().data(users).build();
    }

    /**
     * 创建用户
     *
     *     TRIAL("试用",0.5),
     *     DAY("天卡",24d),
     *     WEEK("周卡",7 * 24d),
     *     MONTH("月卡",31 * 24d),
     *     YEAR("年卡",365 * 24d),
     *     YONGJIU("永久卡",Double.valueOf(Long.MAX_VALUE) );
     */
    @PostMapping
    public ResultDto userCreate(@RequestBody User user){
        userService.userCreate(user.getUsername() , user.getPasswd() , user.getUserType());
        return ResultDto.builder().build();
    }

    /**
     * 修改用户
     */
    @PutMapping
    public ResultDto userModify(@RequestBody User user){
        userService.userModify(user.getUsername() , user.getPasswd() , user.getUserType());
        return ResultDto.builder().build();
    }

    /**
     * 用户登录
     */
    @PostMapping(value = "/login")
    public ResultDto userLogin(@RequestBody User user){
        ResultDto result = ResultDto.builder().build();
        try {
            String jwtToken = userService.userLogin(user.getUsername() , user.getPasswd());
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
