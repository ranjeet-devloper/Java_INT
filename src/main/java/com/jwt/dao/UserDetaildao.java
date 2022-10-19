package com.jwt.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.OneToOne;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jwt.model.JwtRequest;
import com.jwt.model.UserDetails;

@Repository
public interface UserDetaildao extends JpaRepository<UserDetails, Long> {

//	@Query("select u from JwtRequest u Where u.userid=:id")
	
	
	@Query("select u from UserDetails u join u.user a Where a.userid=:id")
	public List<UserDetails> findByForeignKey(@Param("id") Long id);
	
//	@Query("UPDATE UserDetails u join u.user u.name=?,u.phone=?,u.email=?,u.linkedIn,u.created_at=?,u.updated_at=? where ")
//	public void updateUserDetail();
	
}