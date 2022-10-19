package com.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.model.ResponseMessage;

@Repository
public interface TinyUrldao extends JpaRepository<ResponseMessage,Long>{

}
