package com.jwt.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jwt.config.JwtAuthenticationFilter;
import com.jwt.dao.TinyUrldao;
import com.jwt.dao.UserDetaildao;
import com.jwt.dao.Userlogindao;
import com.jwt.dao.Usertokendao;
import com.jwt.dto.UserDetailsDto;
import com.jwt.helper.JwtUtil;
import com.jwt.model.JwtRequest;
import com.jwt.model.ResponseMessage;
import com.jwt.model.SaveUserDetailResponse;
import com.jwt.model.UserDetails;
import com.jwt.model.UserDetailsResponse;
import com.jwt.tinyURL.TinyUrlBody;
import com.jwt.tinyURL.TinyUrlResponse;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

//import java.awt.PageAttributes.MediaType;
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//import javax.websocket.server.PathParam;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.w3c.dom.Document;
//
//import com.google.common.net.HttpHeaders;
//import com.jwt.config.JwtAuthenticationFilter;
//import com.jwt.dao.UserDetaildao;
//import com.jwt.dao.Userlogindao;
//import com.jwt.dao.Usertokendao;
//import com.jwt.dto.UserDetailsDto;
//import com.jwt.helper.JwtUtil;
//import com.jwt.model.JwtRequest;
//import com.jwt.model.JwtResponse;
//import com.jwt.model.SaveUserDetailResponse;
//import com.jwt.model.UserDetails;
//import com.jwt.model.UserDetailsResponse;
//import com.jwt.model.UserLogin;
//import com.jwt.tinyURL.TinyUrlBody;
//import com.jwt.tinyURL.TinyUrlResponse;
//import com.lowagie.text.Font;
//import com.lowagie.text.FontFactory;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.pdf.PdfWriter;
////import com.lowagie.text.pdf.PdfWriter;
////import com.lowagie.text.pdf.PdfWriter;

@RestController
@CrossOrigin(origins = "*")
public class Home {
	
	String puretoken=JwtAuthenticationFilter.tokens;
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	UserDetaildao userdetaildao;
	
	@Autowired
	JwtUtil jwtutil;
	
	@Autowired
	Usertokendao usertokendao;
	
	@Autowired
	Userlogindao userlogindao;
	
	@Autowired
	TinyUrldao tinyUrldao;
	
	@PostMapping("/saveall")
    public ResponseEntity<?> saveUserDetails(@Valid @RequestBody UserDetailsDto userdetailsdto,@RequestHeader 
    		String Authorization)throws Exception
    {		
		
		UserDetails userdetail=new UserDetails();
		
	    userdetail.setId(7L);
		userdetail.setUser(userlogindao.findByUsername(jwtutil.getUsernameFromToken(Authorization.substring(7))));
		userdetail.setName(userdetailsdto.getName());
		userdetail.setPhone(userdetailsdto.getPhone());
		userdetail.setEmail(userdetailsdto.getEmail());
		String tiny= tinyUrl(userdetailsdto.getLinkedIn());
		if(tiny!=null)
		{
		    tinyUrldao.save(new ResponseMessage(1L,userlogindao.findByUsername(jwtutil.getUsernameFromToken(Authorization.substring(7))),tiny, 200,"success"));   
		}
		else
		{
		    tinyUrldao.save(new ResponseMessage(1L,userlogindao.findByUsername(jwtutil.getUsernameFromToken(Authorization.substring(7))),tiny, 401,"error"));
		}
		userdetail.setLinkedIn(tiny);
		userdetail.setCreated_at(LocalDateTime.now());
		userdetail.setUpdated_at(LocalDateTime.now());
		
		List<UserDetails> present=userdetaildao.findByForeignKey(userlogindao.
				findByUsername(jwtutil.getUsernameFromToken(Authorization.substring(7))).getUserid());
		if(present.isEmpty())
		{
			userdetaildao.save(userdetail);
			return new ResponseEntity<>(new SaveUserDetailResponse("200","Successfully added data"),HttpStatus.ACCEPTED);
		}
		else
		{
			present.get(0).setName(userdetailsdto.getName());
			present.get(0).setPhone(userdetailsdto.getPhone());
			present.get(0).setEmail(userdetailsdto.getEmail());
			tiny= tinyUrl(userdetailsdto.getLinkedIn());
			present.get(0).setLinkedIn(tiny);
			present.get(0).setCreated_at(LocalDateTime.now());
			present.get(0).setUpdated_at(LocalDateTime.now());
			userdetaildao.save(present.get(0));
			return new ResponseEntity<>(new SaveUserDetailResponse("200","Successfully data is updated"),HttpStatus.ACCEPTED);
			
	   }
		
   }
	public String tinyUrl(String urlLink) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.ALL));
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        TinyUrlBody tinyUrlBody = new TinyUrlBody();
        tinyUrlBody.setUrl(urlLink);
        HttpEntity<TinyUrlBody> entity = new HttpEntity<TinyUrlBody>(tinyUrlBody, headers);
        String tinyUlr = "https://api.tinyurl.com/create?api_token=xZNcKWT9B0Kapik0WkHZeKEbZN4PTSfqrQj8sCvP9oi3h6p3Iy9s6ymaA9Rc";
        TinyUrlResponse tinyUrlResponse = restTemplate.exchange(tinyUlr, HttpMethod.POST, entity,
        		TinyUrlResponse.class)
                .getBody();
        return tinyUrlResponse.getData().getTiny_url();

    }
	
	@GetMapping("/getuser")
	public UserDetailsResponse getUserDetails(@RequestHeader String Authorization)
	{

		JwtRequest jwtRequestObject=userlogindao.findByUsername
		        (jwtutil.getUsernameFromToken(Authorization.substring(7)));
		
		List<UserDetails> listOfUserDetail=userdetaildao.findByForeignKey(jwtRequestObject.getUserid());
		
			return new UserDetailsResponse(200,"Success",listOfUserDetail);

	}
	
	
@GetMapping("/getall")
public UserDetailsResponse getAllUserDetails()
{
	List<UserDetails> listOfUserDetail=userdetaildao.findAll();
	
		return new UserDetailsResponse(200,"Success",listOfUserDetail);
}

@GetMapping("/pdf/generate")
public void generatePDF(HttpServletResponse response, @RequestHeader String Authorization) throws IOException {

 String token=Authorization.substring(7);
    
    String username=jwtutil.getUsernameFromToken(token);
   JwtRequest userlogin=userlogindao.findByUsername(username);
    List<UserDetails> list = userdetaildao.findByForeignKey(userlogin.getUserid());
    UserDetails userDetails = list.get(0);
    response.setContentType("application/pdf");
    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
    String currentDateTime = dateFormatter.format(new Date());

    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=pdf_"+currentDateTime+".pdf";
    response.setHeader(headerKey, headerValue);
    
    com.lowagie.text.Document document=new com.lowagie.text.Document(PageSize.A4);
    

    PdfWriter.getInstance((com.lowagie.text.Document) document, response.getOutputStream());

    document.open();
    Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
    fontTitle.setSize(18);

    Paragraph paragraph = new Paragraph("Details", fontTitle);
    paragraph.setAlignment(Paragraph.ALIGN_CENTER);

    Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
    fontParagraph.setSize(12);

    Paragraph blank = new Paragraph("       ", fontParagraph);
    blank.setAlignment(blank.ALIGN_CENTER);

    Paragraph id = new Paragraph("id : " +  list.get(0).getId(), fontParagraph);
    id.setAlignment(id.ALIGN_CENTER);
    
    

    Paragraph name = new Paragraph("name : " + userDetails.getName(), fontParagraph);
   name.setAlignment(name.ALIGN_CENTER);

    Paragraph email = new Paragraph("email : " + userDetails.getEmail(), fontParagraph);
    email.setAlignment(Paragraph.ALIGN_CENTER);

    Paragraph phone = new Paragraph("phone : " + userDetails.getPhone(), fontParagraph);
    phone.setAlignment(Paragraph.ALIGN_CENTER);

    Paragraph linkedIn = new Paragraph("linkedIn : " + userDetails.getLinkedIn(), fontParagraph);
    linkedIn.setAlignment(Paragraph.ALIGN_CENTER);

    document.add(paragraph);
    document.add(blank);
    document.add(id);
    document.add(name);
    document.add(email);
    document.add(phone);
    document.add(linkedIn);
    document.close();
}
	
	
}
