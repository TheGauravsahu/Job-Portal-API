package com.jobportal.service;

import com.jobportal.dto.*;
import com.jobportal.exceptions.JobPortalException;


public interface UserService {
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException;

    public LoginResponseDTO loginUser(LoginDTO loginDTO) throws  JobPortalException;

    public UserDTO getCurrentUser(String email) throws  JobPortalException;

    public void sendOtp(String email) throws Exception;

    public Boolean verifyOpt(String email, String otp) throws JobPortalException;

    public UserDTO changePassword(ChangePasswordDTO changePasswordDTO) throws  JobPortalException;
}
