package com.jobportal.controller;

import com.jobportal.dto.*;
import com.jobportal.exceptions.JobPortalException;
import com.jobportal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/users")
public class UserAPI {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws JobPortalException {
        userDTO = userService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException {
        String token = userService.loginUser(loginDTO);
        return ResponseEntity.ok(new LoginResponseDTO("Login successful", token));
    }

    @PutMapping("/change-password")
    public ResponseEntity<UserDTO> changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO) throws JobPortalException {
        return new ResponseEntity<>(userService.changePassword(changePasswordDTO), HttpStatus.OK);
    }

    @PostMapping("/send-otp/{email}")
    public ResponseEntity<ResponseDTO> sendOtp(@PathVariable String email) throws Exception {
        userService.sendOtp(email);
        ResponseDTO res = new ResponseDTO("OTP sent successfully to: " + email);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PostMapping("/verify-otp/{email}/{otp}")
    public ResponseEntity<ResponseDTO> verifyOtp(@PathVariable String email, @PathVariable String otp) throws  JobPortalException{
        userService.verifyOpt(email,otp);
        return new ResponseEntity<>(new ResponseDTO("Otp verified succefully."), HttpStatus.OK);
    }


}
