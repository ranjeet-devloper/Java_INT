package com.jwt.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.dao.Userlogindao;
import com.jwt.model.JwtRequest;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	Userlogindao userlogindao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
//		Without database
//		if(username.equals("ranjeet"))
//		{
//			return new User("ranjeet","int@123",new ArrayList<>());
//		}
//		else
//			 throw new UsernameNotFoundException("user not found");
//	
//		with database
		JwtRequest user= userlogindao.findByUsername(username);
		if(user==null)
		{
		  throw new UsernameNotFoundException("Invailed user!!!");
		}
		else
		{
			return new CustomUserDetails(user);
		}
	}

	

	

}
