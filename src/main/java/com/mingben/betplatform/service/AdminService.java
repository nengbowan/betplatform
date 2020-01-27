package com.mingben.betplatform.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.mingben.betplatform.entity.Admin;
import com.mingben.betplatform.entity.User;
import com.mingben.betplatform.enums.UserTypeEnum;
import com.mingben.betplatform.exception.BussinessException;
import com.mingben.betplatform.exception.UserNotExistException;
import com.mingben.betplatform.exception.UserPasswordNotMatchException;
import com.mingben.betplatform.repository.AdminRepository;
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

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    /**
     * 管理员登录
     * @param username
     * @param passwd
     */
    public String adminLogin(String username, String passwd) throws UserNotExistException, UserPasswordNotMatchException {
        Admin admin = adminRepository.findByUsername(username);
        if(admin == null){
            throw new UserNotExistException();
        }
        Admin matched = adminRepository.findByUsernameAndPasswd(username , passwd);
        if(matched == null){
            throw new UserPasswordNotMatchException();
        }

        String token = JwtTokenUtil.createToken(username , passwd ,  "admin");
        return token;
    }

    public Admin findByUsernameAndPasswd(String username, String passwd){
        return adminRepository.findByUsernameAndPasswd(username , passwd);
    }

    public Admin findByUsername(String username){
        return adminRepository.findByUsername(username);
    }
}
