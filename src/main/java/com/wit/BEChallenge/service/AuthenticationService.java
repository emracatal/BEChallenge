package com.wit.BEChallenge.service;

import com.wit.BEChallenge.dto.LoginRequest;
import com.wit.BEChallenge.dto.UserResponse;
import com.wit.BEChallenge.entity.Role;
import com.wit.BEChallenge.entity.User;
import com.wit.BEChallenge.exceptions.CommerceException;
import com.wit.BEChallenge.repository.RoleRepository;
import com.wit.BEChallenge.repository.UserRepository;
import com.wit.BEChallenge.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse register(String fullName, String email, String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("customer").get();

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setAuthorities(roles);

        return Converter.UserConverter(userRepository.save(user));
    }

    public UserResponse login(LoginRequest loginRequest){
        Optional<User> optionalUser = userRepository.findUserByEmail(loginRequest.email());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            System.out.println(user.getPassword() + loginRequest.password());
            boolean isPasswordSame = passwordEncoder.matches(loginRequest.password(),user.getPassword());
            if(isPasswordSame){
                return Converter.UserConverter(user);
            }
            throw new CommerceException("Invalid Credantials", HttpStatus.BAD_REQUEST);
        }
        throw new CommerceException("Invalid Credantials", HttpStatus.BAD_REQUEST);
    }
}
