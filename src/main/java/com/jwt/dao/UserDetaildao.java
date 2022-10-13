package com.jwt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jwt.model.UserDetails;

@Repository
public interface UserDetaildao extends JpaRepository<UserDetails, Long> {

//	@Query("select u from JwtRequest u Where u.userid=:id")
	
	
	@Query("select u from UserDetails u join u.user a Where a.userid=:id")
	public List<UserDetails> findByForeignKey(@Param("id") Long id);
	
}
