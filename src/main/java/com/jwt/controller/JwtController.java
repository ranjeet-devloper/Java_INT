package com.jwt.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.dao.Usertokendao;
import com.jwt.helper.JwtUtil;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.model.UserLogin;
import com.jwt.services.CustomUserDetailsService;


@RestController
public class JwtController {
	
	@Autowired
	private Usertokendao usertokendao;
	

	@Autowired
	private  AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@PostMapping("/token")
	public JwtResponse generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		System.out.println(jwtRequest);
		
		try
		{
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return new JwtResponse(401,"Not Authorized","");
			
		}
		
		//fine area
//		UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token=jwtutil.generateToken(jwtRequest.getUsername());
		UserLogin userlogin=new UserLogin(jwtRequest, token, "201", LocalDateTime.now());
		usertokendao.save(userlogin);
		
//		System.out.println("hii"+userlogin);
//		return ResponseEntity.ok(userlogin);
		
//		System.out.println(new JwtResponse(201,"sucessfully authenticate",token));
		
//		System.out.println("jay shree ram");
       return new JwtResponse(200,"Login Success",token);
	}
	
}
