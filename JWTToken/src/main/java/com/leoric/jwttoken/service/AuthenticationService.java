package com.leoric.jwttoken.service;

import com.leoric.jwttoken.dto.LoginUserDto;
import com.leoric.jwttoken.dto.RegisterUserDto;
import com.leoric.jwttoken.dto.VerifiedUserDto;
import com.leoric.jwttoken.model.User;
import com.leoric.jwttoken.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 PasswordEncoder passwordEncoder,
                                 UserRepository userRepository,
                                 EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }
    public User signUp(RegisterUserDto input){
        User user = new User(input.getUsername(), input.getEmail(),passwordEncoder.encode(input.getPassword()));
        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(60));
        user.setEnabled(true);
        sendVerificationEmail(user);
        return userRepository.save(user);
    }
    public User authenticate(LoginUserDto input){
        User user = userRepository.findByEmail(input.getEmail()).
                orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isEnabled()) {
            throw new RuntimeException("Account not verified. Please verify your account.");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
        return user;
    }
    public void verifyUser(VerifiedUserDto input){
        Optional<User> optUser = userRepository.findByEmail(input.getEmail());
        if(optUser.isPresent()){
            User user = optUser.get();
            if (user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Verification code has expired.");
            }
            if (user.getVerificationCode().equals(input.getVerificationCode())) {
                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpiresAt(null);
                userRepository.save(user);
            } else {
                throw new RuntimeException("Invalid verification code.");
            }
        } else {
            throw new RuntimeException("User not found.");
        }
    }

}
