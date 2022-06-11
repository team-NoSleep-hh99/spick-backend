package com.team.spick.service;

import com.team.spick.domain.User;
import com.team.spick.dto.SignupRequestDto;
import com.team.spick.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();

        //회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);

        String pattern = "^[a-zA-Z0-9]*$";
        String pattern2 = "^[A-Za-z0-9#?!@$ %^&*-]*$";
//        ^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-])*$

        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디 입니다.");
        } else if (!Pattern.matches(pattern, username)) {
            throw new IllegalArgumentException("영문, 숫자로만 입력하세요");
        } else if (nickname.length() > 6 || nickname.length() < 2) {
            throw new IllegalArgumentException("닉네임은 2자~6자범위로 입력해주세요");
        } else if (!Pattern.matches(pattern2, password)) {
            throw new IllegalArgumentException("비밀변호는 대소문자숫자특수문자를 포함해야합니다");
        } else if (password.length() < 8) {
            throw new IllegalArgumentException("비밀번호를 8자 이상 입력하세요");
        } else if (password.contains(username)) {
            throw new IllegalArgumentException("비밀번호에 ID를 포함할 수 없습니다.");
        } else if (!passwordCheck.equals(password)) {
            throw new IllegalArgumentException("비밀번호와 정확히 일치하게 작성해주세요");
        }

        User user = new User(requestDto);

        return userRepository.save(user);

    }
}
