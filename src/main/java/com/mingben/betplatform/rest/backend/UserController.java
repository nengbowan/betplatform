package com.mingben.betplatform.rest.backend;

import com.mingben.betplatform.dto.ResultDto;
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
     * @param username
     * @param passwd
     * @param userType
     *
     *     TRIAL("试用",0.5),
     *     DAY("天卡",24d),
     *     WEEK("周卡",7 * 24d),
     *     MONTH("月卡",31 * 24d),
     *     YEAR("年卡",365 * 24d),
     *     YONGJIU("永久卡",Double.valueOf(Long.MAX_VALUE) );
     */
    @PostMapping
    public ResultDto userCreate(@RequestParam(name = "username") String username,
                                @RequestParam(name = "passwd")String passwd,
                                @RequestParam(name = "userType")String userType){
        userService.userCreate(username , passwd , userType);
        return ResultDto.builder().build();
    }

    /**
     * 修改用户
     * @param username
     * @param passwd
     * @param userType
     */
    @PutMapping
    public ResultDto userModify(@RequestParam(name = "username") String username,
                                @RequestParam(name = "passwd")String passwd,
                                @RequestParam(name = "userType")String userType){
        userService.userModify(username , passwd , userType);
        return ResultDto.builder().build();
    }

    /**
     * 管理员登录
     * @param username
     * @param passwd
     */
    @PostMapping(value = "/login")
    public ResultDto userLogin(@RequestParam(name = "username") String username,
                               @RequestParam(name = "passwd")String passwd){
        ResultDto result = ResultDto.builder().build();
        try {
            String jwtToken = userService.userLogin(username , passwd);
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
