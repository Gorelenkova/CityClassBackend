package com.cityClass.CityClass.Service;

import com.cityClass.CityClass.Model.User;
import com.cityClass.CityClass.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceSecurity {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Шифруем пароль
        return userRepository.save(user);
    }
    public Optional<User> findByMail(String email){
        return userRepository.findByMail(email);
    }
}
