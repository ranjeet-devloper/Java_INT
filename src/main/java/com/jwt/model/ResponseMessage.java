package com.jwt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ResponseMessage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private JwtRequest user;
    private String tinyurl;
    private Integer statuscode;
    private String message;
    
    public ResponseMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResponseMessage(Long id, JwtRequest user, String tinyurl, Integer statuscode, String message) {
        super();
        this.id = id;
        this.user = user;
        this.tinyurl = tinyurl;
        this.statuscode = statuscode;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JwtRequest getUser() {
        return user;
    }

    public void setUser(JwtRequest user) {
        this.user = user;
    }

    public String getTinyurl() {
        return tinyurl;
    }

    public void setTinyurl(String tinyurl) {
        this.tinyurl = tinyurl;
    }

    public Integer getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(Integer statuscode) {
        this.statuscode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseMessage [id=" + id + ", user=" + user + ", tinyurl=" + tinyurl + ", statuscode=" + statuscode
                + ", message=" + message + "]";
    }

   
    

}
