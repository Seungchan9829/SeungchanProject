package ksc.ts.dto;


import lombok.Getter;

@Getter
public class LoginRequest {

    private String userEmail;

    private String password;
}
