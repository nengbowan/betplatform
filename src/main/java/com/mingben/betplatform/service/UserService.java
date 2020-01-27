package com.mingben.betplatform.service;

import com.mingben.betplatform.entity.Admin;
import com.mingben.betplatform.entity.User;
import com.mingben.betplatform.enums.UserTypeEnum;
import com.mingben.betplatform.exception.BussinessException;
import com.mingben.betplatform.exception.UserNotExistException;
import com.mingben.betplatform.exception.UserPasswordNotMatchException;
import com.mingben.betplatform.repository.UserRepository;
import com.mingben.betplatform.util.DateUtil;
import com.mingben.betplatform.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 查询用户
     * @param username
     */
    public Page<List<User>> userQuery(Pageable pageable , String username) {
        Page<List<User>> users = userRepository.findByUsernameLike(pageable , "%"+username+"%");
        return users;
    }

    /**
     * 创建用户
     * @param username
     * @param passwd
     * @param userType 试用 天卡 周卡 月卡 年卡 永久卡
     */
    @PostMapping
    public void userCreate(String username, String passwd , String userType) {
        Date now = new Date();
        UserTypeEnum typeEnum = UserTypeEnum.findByUserType(userType);
        Date expiredDate = DateUtil.addHour(now ,typeEnum.getHour());
        User user = User.builder()
                .username(username)
                .passwd(passwd)
                .userType(userType)
                .expiredDate(expiredDate)
                .createTime(now)
                .online(false)
                .build();
        userRepository.save(user);
    }

    /**
     * 修改用户
     * @param username
     * @param passwd
     * @param userType
     */
    public void userModify(String username, String passwd, String userType) {
        User user = userRepository.findOne(Example.of(User.builder().username(username).build())).get();
        if(user == null){
            throw new BussinessException("修改用户,用户不存在");
        }
        user.setPasswd(passwd);
        user.setUserType(userType);
        UserTypeEnum typeEnum = UserTypeEnum.findByUserType(userType);
        Date expiredDate = DateUtil.addHour(user.getCreateTime() ,typeEnum.getHour());
        user.setExpiredDate(expiredDate);
        userRepository.save(user);
    }

    /**
     * 用户登录
     * @param username
     * @param passwd
     */
    public String userLogin(String username, String passwd) throws UserNotExistException, UserPasswordNotMatchException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserNotExistException();
        }
        User matched = userRepository.findByUsernameAndPasswd(username , passwd);
        if(matched == null){
            throw new UserPasswordNotMatchException();
        }

        String token = JwtTokenUtil.createToken(username , passwd ,  "user");
        return token;
    }

    public User findByUsernameAndPasswd(String username, String passwd){
        return userRepository.findByUsernameAndPasswd(username , passwd);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
