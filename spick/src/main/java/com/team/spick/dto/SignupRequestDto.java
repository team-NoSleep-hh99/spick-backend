package com.team.spick.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;
    private String nickname;
    private String password;
    private String passwordCheck;

    private String user_picURL;

    public SignupRequestDto(String username, String nickname, String password, String passwordCheck, String user_picURL) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.user_picURL = user_picURL;
    }


}
