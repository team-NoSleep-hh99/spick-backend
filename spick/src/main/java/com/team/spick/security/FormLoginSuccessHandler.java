package com.team.spick.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.spick.domain.User;
import com.team.spick.dto.LoginRequestDto;
import com.team.spick.repository.UserRepository;
import com.team.spick.security.jwt.JwtTokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "BEARER";

    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {
        final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
        // Token 생성
        final String token = JwtTokenUtils.generateJwtToken(userDetails);
        System.out.println("FormLoginSuccessHandler token = " + token);
        response.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + token);
        response.addHeader("Username", userDetails.getUser().getUsername());
        response.addHeader("Nickname", userDetails.getUser().getNickname());


        //UserId 내려주기
//        response.setContentType("application/json");
//        User user = userDetails.getUser();
//        UserDto responseDto = new UserDto(user);
//
//        String result = mapper.writeValueAsString(responseDto);
//        response.getWriter().write(result);
    }

}
