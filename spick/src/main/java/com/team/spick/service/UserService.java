package com.team.spick.service;

import com.team.spick.domain.User;
import com.team.spick.dto.SignupRequestDto;
import com.team.spick.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();
//        String user_picURL = requestDto.getUser_picURL();

        //회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);

        String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        String pattern2 = "^[a-z0-9#?!@$ %^&*-]*$";
//        ^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-])*$
//
//        if (found.isPresent()) {
//            throw new IllegalArgumentException("중복된 아이디 입니다.");
//        }
        //이메일 형식이랑, 대문자 없애고
        if (!Pattern.matches(pattern, username)) {
            throw new IllegalArgumentException("이메일 형식으로 작성해주세요 입력하세요");
        } else if (nickname.length() > 6 || nickname.length() < 1) {
            throw new IllegalArgumentException("닉네임은 1자~6자범위로 입력해주세요");
        } else if (!Pattern.matches(pattern2, password)) {
            throw new IllegalArgumentException("비밀변호는 소문자숫자특수문자를 포함해야합니다");
        } else if (password.length() < 8) {
            throw new IllegalArgumentException("비밀번호를 8자 이상 입력하세요");
        } else if (!passwordCheck.equals(password)) {
            throw new IllegalArgumentException("비밀번호와 정확히 일치하게 작성해주세요");
        }

        //패스워드 암호화
        password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, nickname, password);
        System.out.println(requestDto);

        return userRepository.save(user);

    }

    public boolean checkId(SignupRequestDto signupRequestDto) {
//        User user = userRepository.findByUsername(signupRequestDto.getUsername()).isPresent();
//        boolean result = false;
//        if (user == null) {
//            result = true;
//            return result;
//        }
        return !userRepository.findByUsername(signupRequestDto.getUsername()).isPresent(); //by 서만원
    }

    public boolean checkNick(SignupRequestDto signupRequestDto) {
//        User user = userRepository.findByNickname(signupRequestDto.getNickname()).isPresent();
//        boolean result = false;
//        if (user == null) {
//            result = true;
//            return result;
//        }
        return !userRepository.findByNickname(signupRequestDto.getNickname()).isPresent();  //by 서만원
    }
}
