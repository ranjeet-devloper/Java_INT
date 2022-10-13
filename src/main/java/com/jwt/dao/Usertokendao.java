package com.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.model.UserLogin;
@Repository
public interface Usertokendao extends JpaRepository<UserLogin, Long> {

}
