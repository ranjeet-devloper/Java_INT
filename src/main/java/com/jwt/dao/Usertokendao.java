package com.jwt.dao;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jwt.model.UserLogin;
@Repository
public interface Usertokendao extends JpaRepository<UserLogin, Long> {

	
	@Query("select u from UserLogin u where u.token=:token")
	public UserLogin findByAuthToken(@Param("token") String token);

}
