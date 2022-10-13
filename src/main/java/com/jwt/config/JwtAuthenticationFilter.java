//package com.jwt.config;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.jwt.helper.JwtUtil;
//import com.jwt.services.CustomUserDetailsService;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//	@Autowired 
//	CustomUserDetailsService customUserDetailsService;
//	
//	@Autowired
//	JwtUtil jwtutil;
//	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//	//get jwttoken
//		
//		System.out.println("work is not fine");
//		
//		String requestTokenHeader=request.getHeader("Authorization");
//		String username=null;
//		String jwttoken=null;
//		
//	System.out.println("hello="+request.getHeader("Authorization"));
//		
//		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
//
//		{
//			
//			System.out.println("token shi h");
//			
//		   jwttoken=requestTokenHeader.substring(7);
//		   
//		   try
//		   {
//			   username=jwtutil.getUsernameFromToken(jwttoken);
//			   
//		   }catch(Exception e)
//		   {
//			  e.printStackTrace();
//		   }
//		   
//		  
//		UserDetails userdetails=this.customUserDetailsService.loadUserByUsername(username);
//		
//		if(username!=null && SecurityContextHolder.getContext()==null)
//		{
//		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userdetails, null,userdetails.getAuthorities());
//		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//		}
//		else
//		{
//			System.out.println("unknown user");
//		}
//		}
//		
//		
//		System.out.println("work is fine");
//		filterChain.doFilter(request, response);
//	}
//
//}




//package com.jwt.config;
//
//import com.jwt.helper.JwtUtil;
//import com.jwt.services.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter  extends OncePerRequestFilter {
//
//
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        //get jwt
//        //Bearer
//        //validate
//        String requestTokenHeader = request.getHeader("Authorization");
//        String username=null;
//        String jwtToken=null;
//
//        //null and format
//        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
//        {
//            jwtToken=requestTokenHeader.substring(7);
//
//            try{
//
//                username = this.jwtUtil.getUsernameFromToken(jwtToken);
//
//
//            }catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
//            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
//            {
//
//                UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
//                //security
//
//
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//
//            }else
//            {
//                System.out.println("Token is not validated..");
//            }
//
//
//
//
//        }
//
//
//        filterChain.doFilter(request,response);
//
//
//    }
//}
//


package com.jwt.config;

import com.jwt.helper.JwtUtil;
import com.jwt.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter {
   
	public static String tokens;
	
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //get jwt
        //Bearer
        //validate
        String requestTokenHeader = request.getHeader("Authorization");
        String username=null;
        String jwtToken=null;

        //null and format
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
        {
        	tokens=requestTokenHeader.substring(7);
        	
        	System.out.println(tokens);
        	
            jwtToken=requestTokenHeader.substring(7);

            try{

                username = this.jwtUtil.getUsernameFromToken(jwtToken);


            }catch (Exception e)
            {
                e.printStackTrace();
            }

            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {

                UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
                //security


                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


            }else
            {
                System.out.println("Token is not validated..");
            }




        }


        filterChain.doFilter(request,response);


    }
}

