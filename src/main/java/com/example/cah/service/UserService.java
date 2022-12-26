package com.example.cah.service;

import com.example.cah.model.RoleType;
import com.example.cah.model.User;
import com.example.cah.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void 회원가입(User user){

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(RoleType.USER);
            userRepository.save(user);

    }

    @Transactional(readOnly = true)
    public User 회원찾기(String email){

        User user = userRepository.findByEmail(email).orElseGet(()->{
            return  new User();
        });
        return  user;
    }
}
