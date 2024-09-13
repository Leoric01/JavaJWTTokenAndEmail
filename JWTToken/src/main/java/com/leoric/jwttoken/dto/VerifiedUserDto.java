package com.leoric.jwttoken.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifiedUserDto {
    private String email;
    private String verificationCode;
}
