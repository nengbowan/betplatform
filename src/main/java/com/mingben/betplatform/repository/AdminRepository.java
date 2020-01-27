package com.mingben.betplatform.repository;

import com.mingben.betplatform.entity.Admin;
import com.mingben.betplatform.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin,String> {
    public Admin findByUsernameAndPasswd(String username, String passwd);

    public Admin findByUsername(String username);
}
