package com.mingben.betplatform.repository;

import com.mingben.betplatform.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    public Page<List<User>> findByUsernameLike(Pageable pageable , String username);
}
