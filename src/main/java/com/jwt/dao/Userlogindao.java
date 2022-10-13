package com.jwt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jwt.model.JwtRequest;

public interface Userlogindao extends JpaRepository<JwtRequest, Long> {

    public JwtRequest findByUsername(String username);
    
    @Query("select u from JwtRequest u Where u.userid=:id")
    public JwtRequest getUserById(@Param("id") Long id);
}
