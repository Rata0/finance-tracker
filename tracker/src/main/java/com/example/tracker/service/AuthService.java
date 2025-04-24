package com.example.tracker.service;

import com.example.tracker.dto.LoginDTO;
import com.example.tracker.dto.RegistrationDTO;
import com.example.tracker.exception.UserAlreadyExistsException;
import com.example.tracker.mapper.UserMapper;
import com.example.tracker.model.User;
import com.example.tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(RegistrationDTO registrationDTO) {
        if (userRepository.existsByUsername(registrationDTO.getUsername())) {
            throw new UserAlreadyExistsException("Username already in use");
        }

        User user = userMapper.map(registrationDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public String verify(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                ));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginDTO.getUsername());
        }

        return "Fail authentication";
    }
}
