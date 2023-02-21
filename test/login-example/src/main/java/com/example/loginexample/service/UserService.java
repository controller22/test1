package com.example.loginexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.loginexample.dto.UserReq.JoinReqDto;
import com.example.loginexample.dto.UserReq.LoginReqDto;
import com.example.loginexample.handler.ex.CustomException;
import com.example.loginexample.model.User;
import com.example.loginexample.model.UserRepository;
import com.example.loginexample.util.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(JoinReqDto joinReqDto) {
        User sameUser = userRepository.findByUsername(joinReqDto.getUsername());
        if (sameUser != null) {
            throw new CustomException("동일한 username이 존재합니다");
        }

        int result = userRepository.insert(joinReqDto.getUsername(), PasswordEncoder.encode(joinReqDto.getPassword()),
                joinReqDto.getEmail());
        if (result != 1) {
            throw new CustomException("회원가입실패");
        }
    }

    @Transactional(readOnly = true)
    public User 로그인(LoginReqDto loginReqDto) {
        User principal = null;
        try {
            principal = userRepository.findByUsernameAndPassword(
                    loginReqDto.getUsername(), PasswordEncoder.encode(loginReqDto.getPassword()));
        } catch (Exception e) {
            throw new CustomException("로그인실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (principal == null) {
            throw new CustomException("유저네임 혹은 패스워드가 잘못 입력되었습니다");
        }
        return principal;
    };

}
