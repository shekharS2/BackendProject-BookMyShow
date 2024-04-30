package dev.shekhar.BookMyShow.service;

import dev.shekhar.BookMyShow.model.User;
import dev.shekhar.BookMyShow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User signup(String name, String email, String password) throws Exception {
        User savedUser = userRepository.findUserByEmail(email);
        if(savedUser != null) {
            throw new Exception("User with same email exists");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setTickets(new ArrayList<>());
        return userRepository.save(user);
    }

    public User login(String email, String password) throws Exception {
        User savedUser = userRepository.findUserByEmail(email);
        if(savedUser == null){
            throw new Exception("User with email does not exist");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(password, savedUser.getPassword())){
            return savedUser;
        }
        throw new Exception("Invalid password");
    }

}
