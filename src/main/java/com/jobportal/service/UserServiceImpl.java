package com.jobportal.service;

import com.jobportal.dto.ChangePasswordDTO;
import com.jobportal.dto.LoginDTO;
import com.jobportal.dto.UserDTO;
import com.jobportal.entity.Otp;
import com.jobportal.entity.User;
import com.jobportal.exceptions.JobPortalException;
import com.jobportal.repository.OtpRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.utility.Data;
import com.jobportal.utility.JwtUtils;
import com.jobportal.utility.Utilities;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) throw new JobPortalException("USER_FOUND");

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userDTO.toEntity();
        user = userRepository.save(user);
        return user.toDTO();
    }

    @Override
    public String loginUser(LoginDTO loginDTO) throws JobPortalException {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            throw new JobPortalException("INVALID_CREDENTIAL");

        return jwtUtils.generateToken(user.getEmail());
    }

    @Override
    public UserDTO changePassword(ChangePasswordDTO changePasswordDTO) throws JobPortalException {
        User user = userRepository.findByEmail(changePasswordDTO.getEmail())
                .orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));

        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword()))
            throw new JobPortalException("INVALID_CREDENTIAL");


        user.setPassword(passwordEncoder.encode(changePasswordDTO.getPassword()));
        userRepository.save(user);

        return user.toDTO();
    }

    @Override
    public void sendOtp(String email) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        String genOtp = Utilities.generateOTP();

        Otp otp = new Otp(email, genOtp, LocalDateTime.now());
        otpRepository.save(otp);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Your OTP Code");
        helper.setText(Data.getMessageBody(genOtp, user.getName()), true);

        mailSender.send(message);
    }

    @Override
    public Boolean verifyOpt(String email, String otp) throws JobPortalException {
        Otp dbOtp = otpRepository.findById(email).orElseThrow(() -> new JobPortalException("INVALID_OTP"));
        if (!dbOtp.getOtp().equals(otp)) {
            throw new JobPortalException("INVALID_OTP");
        }

        User user = userRepository.findByEmail(email).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        user.setIsVerified(true);
        userRepository.save(user);
        return true;
    }

    @Scheduled(fixedRate = 60000) // 1 minute
    public void removeExpiredOtp() {
        LocalDateTime expiry = LocalDateTime.now().minusMinutes(5);
        List<Otp> expiredOpts = otpRepository.findByCreatedAtBefore(expiry);

        if (!expiredOpts.isEmpty()) {
            System.out.println("Removed " + expiredOpts.size() + " OTPs.");
            otpRepository.deleteAll(expiredOpts);
        }
    }

}

