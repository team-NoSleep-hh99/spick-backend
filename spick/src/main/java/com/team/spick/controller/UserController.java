package com.team.spick.controller;

import com.team.spick.domain.User;
import com.team.spick.dto.SignupRequestDto;
import com.team.spick.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor //생성자 주입
@RestController // Json 객체로 반환
public class UserController {

    private final UserService userService;

    @PostMapping("/api/signup")
    public User createUser(@RequestBody SignupRequestDto requestDto) {

        return userService.registerUser(requestDto);

//        try {
//            userService.registerUser(requestDto);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
    }
    @PostMapping("/api/checkId")
    public boolean duplicateId(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.checkId(signupRequestDto);
    }

    @PostMapping("/api/checkNick")
    public boolean duplicateNickname(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.checkNick(signupRequestDto);
    }


}
