package com.jwt.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.config.JwtAuthenticationFilter;
import com.jwt.dao.UserDetaildao;
import com.jwt.dao.Userlogindao;
import com.jwt.dto.UserDetailsDto;
import com.jwt.helper.JwtUtil;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.model.SaveUserDetailResponse;
import com.jwt.model.UserDetails;
import com.jwt.model.UserDetailsResponse;

@RestController
@CrossOrigin(origins = "*")
public class Home {
	
	String puretoken=JwtAuthenticationFilter.tokens;
	
	@Autowired
	UserDetaildao userdetaildao;
	
	@Autowired
	JwtUtil jwtutil;
	
	
	@Autowired
	Userlogindao userlogindao;
	@GetMapping("/home")
	public String homePage()
	{
		String puretoken=JwtAuthenticationFilter.tokens;
		
//		String s=puretoken;
		return puretoken+"this is authorized useruigyoyyyyyyyyyyyyyyyyyyy";
	}
	
	
	@PostMapping("/saveall")
    public ResponseEntity<?> saveUserDetails(@RequestBody UserDetailsDto userdetailsdto)throws Exception
    {
		
		UserDetails userdetail=new UserDetails();
		
		userdetail.setUser(userlogindao.getUserById(userdetailsdto.getId()));
		userdetail.setName(userdetailsdto.getName());
		userdetail.setPhone(userdetailsdto.getPhone());
		userdetail.setEmail(userdetailsdto.getEmail());
		userdetail.setLinkedIn(userdetailsdto.getLinkedIn());
		userdetail.setCreated_at(LocalDateTime.now());
		userdetail.setUpdated_at(LocalDateTime.now());
		
		UserDetails details=userdetaildao.save(userdetail);
		if(details!=null)
		{
			return new ResponseEntity<>(new SaveUserDetailResponse("200","Successfully added the data"),HttpStatus.ACCEPTED);
		}
		else
		{
			return new ResponseEntity<>(new JwtResponse(401,"Not Authorized",""),HttpStatus.ACCEPTED);
		}
		
    }
	
	@GetMapping("/getuser")
	public UserDetailsResponse getUserDetails(@RequestHeader String Authorization)
	{
//		System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii"+Authorization.substring(7));
		System.out.println(jwtutil.getUsernameFromToken(Authorization.substring(7)));
		JwtRequest jwtRequestObject=userlogindao.findByUsername(jwtutil.getUsernameFromToken(Authorization.substring(7)));
		
//		System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii"+jwtRequestObject);
		List<UserDetails> listOfUserDetail=userdetaildao.findByForeignKey(jwtRequestObject.getUserid());
		
//		System.out.println("tiktok=="+listOfUserDetail);
		
//		if(listOfUserDetail!=null)
			return new UserDetailsResponse(200,"Success",listOfUserDetail);
//		else
//			return new JwtResponse()
		/* return userdetaildao.findAll(); */
//		return null;
	}
	
	
@GetMapping("/getall")
public UserDetailsResponse getAllUserDetails()
{
	List<UserDetails> listOfUserDetail=userdetaildao.findAll();
	
//	System.out.println("tiktok=="+listOfUserDetail);
	
//	if(listOfUserDetail!=null)
		return new UserDetailsResponse(200,"Success",listOfUserDetail);
//	else
//		return new JwtResponse()
	/* return userdetaildao.findAll(); */
//	return null;
}


	
	
}
