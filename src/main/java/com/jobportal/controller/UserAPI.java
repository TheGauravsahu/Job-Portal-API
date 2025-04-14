package com.jobportal.controller;

import com.jobportal.dto.*;
import com.jobportal.exceptions.JobPortalException;
import com.jobportal.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/users")
public class UserAPI {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO, HttpServletResponse response) throws JobPortalException {
        UserDTO registerUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody @Valid LoginDTO loginDTO, HttpServletResponse response) throws JobPortalException {
        LoginResponseDTO loginResponseDTO = userService.loginUser(loginDTO);

        // set token to cookies
        Cookie cookie = new Cookie("token", loginResponseDTO.getToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // use true in production
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60); // 1 day

        response.addCookie(cookie);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PutMapping("/change-password")
    public ResponseEntity<UserDTO> changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO) throws JobPortalException {
        return new ResponseEntity<>(userService.changePassword(changePasswordDTO), HttpStatus.OK);
    }

    @PostMapping("/send-otp/{email}")
    public ResponseEntity<ResponseDTO> sendOtp(@PathVariable String email) throws Exception {
        userService.sendOtp(email);
        ResponseDTO res = new ResponseDTO("OTP sent successfully to: " + email);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/verify-otp/{email}/{otp}")
    public ResponseEntity<ResponseDTO> verifyOtp(@PathVariable String email, @PathVariable String otp) throws JobPortalException {
        userService.verifyOpt(email, otp);
        return new ResponseEntity<>(new ResponseDTO("Otp verified succefully."), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // expires immediately
        response.addCookie(cookie);

        return new ResponseEntity<>(new ResponseDTO("Logged out succefully."), HttpStatus.OK);
    }

}
