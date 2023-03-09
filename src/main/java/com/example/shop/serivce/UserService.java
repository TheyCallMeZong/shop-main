package com.example.shop.serivce;

import com.example.shop.extentions.exeptions.UserExistException;
import com.example.shop.extentions.exeptions.UserNotFoundException;
import com.example.shop.models.User;
import com.example.shop.models.dto.UserAuth;
import com.example.shop.models.dto.UserRegistration;
import com.example.shop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        modelMapper = new ModelMapper();
    }

    public User authorize(UserAuth userAuth) throws UserNotFoundException {
        if (userAuth.getLogin().isEmpty() || userAuth.getPassword().isEmpty()){
            return null;
        }

        Optional<User> user = userRepository.getUserByEmailAndPassword(userAuth.getLogin(),
                getPasswordHash(userAuth.getPassword()));
        if (user.isEmpty()){
            throw new UserNotFoundException("User with login - {" + userAuth.getLogin() + "} and password - {"
                    + userAuth.getPassword() + "} not found" );
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

    public void registrationUser(UserRegistration userRegistration) throws UserExistException {
        Optional<User> userCheck = userRepository.getUserByEmail(userRegistration.getEmail());
        if (userCheck.isPresent()){
            throw new UserExistException("User with email - {" + userRegistration.getEmail() + "} contains in database" );
        }

        User user = modelMapper.map(userRegistration, User.class);
        user.setPassword(getPasswordHash(user.getPassword()));
        userRepository.save(user);
    }
}
