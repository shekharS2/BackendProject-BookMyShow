package dev.shekhar.BookMyShow.controller;

import dev.shekhar.BookMyShow.dto.UserLoginRequestDTO;
import dev.shekhar.BookMyShow.dto.UserSignupRequestDTO;
import dev.shekhar.BookMyShow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserSignupRequestDTO userSignupRequestDTO) throws Exception {
        // validate user data
         return ResponseEntity.ok(
             userService.signup(
                 userSignupRequestDTO.getName(),
                 userSignupRequestDTO.getEmail(),
                 userSignupRequestDTO.getPassword()
            )
         );
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequestDTO requestDto) throws Exception {
        //validate the userdata
        return ResponseEntity.ok(
            userService.login(
                requestDto.getEmail(),
                requestDto.getPassword()
            )
        );
    }
}
