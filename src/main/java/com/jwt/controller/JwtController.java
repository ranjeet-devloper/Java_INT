package com.jwt.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.jwt.exception.UserException;
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
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{	
		try
		{
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		    throw new UserException("Not Authorized");
		}
		
		String token=jwtutil.generateToken(jwtRequest.getUsername());
		UserLogin userlogin=new UserLogin(jwtRequest, token, "201", LocalDateTime.now());
		usertokendao.save(userlogin);
		JwtResponse jr=new JwtResponse(200,"Login Success",token);

       return new ResponseEntity<JwtResponse>(jr,HttpStatus.ACCEPTED);
	}
	
}
