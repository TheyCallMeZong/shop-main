package com.example.shop.serivce;

import com.example.shop.extentions.exeptions.UserNotFoundException;
import com.example.shop.models.User;
import com.example.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User authorize(String login, String password) throws UserNotFoundException {
        if (login.isEmpty() || password.isEmpty()){
            return null;
        }
        Optional<User> user = userRepository.getUserByLoginAndPassword(login, getPasswordHash(password));
        if (user.isEmpty()){
            throw new UserNotFoundException("User with login - {" + login + "} and password - {" + password + "}" +
                    " not found" );
        }

        return user.get();
    }

    private String getPasswordHash(String password){
        MessageDigest messageDigest;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] bytes = messageDigest.digest(password.getBytes());
            stringBuilder = new StringBuilder();
            for (byte b : bytes) {
                stringBuilder.append(String.format("%02X", b));
            }
        } catch (NoSuchAlgorithmException exception){
            System.out.println(exception.getMessage());
        }

        return stringBuilder.toString();
    }
}
