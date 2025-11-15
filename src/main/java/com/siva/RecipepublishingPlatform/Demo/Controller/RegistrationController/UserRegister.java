package com.siva.RecipepublishingPlatform.Demo.Controller.RegistrationController;

//mport com.siva.RecipepublishingPlatform.Demo.Entity.User;
import com.siva.RecipepublishingPlatform.Demo.Entity.UserDto;
import com.siva.RecipepublishingPlatform.Demo.Entity.Users;
import com.siva.RecipepublishingPlatform.Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserRegister {

    //how to whitelist the "/register" endpoint so that it can be accessed without authentication?
    // we need to configure the security settings in the SecurityConfig class


    @Autowired
    private UserService userService;

    //we can use DTO(Data Transfer Object) to transfer the data from client to server
    //controller method to register the user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){

        Users created = userService.registerUser(userDto);
        return ResponseEntity.ok(Map.of("username", created.getUsername(), "email", created.getEmail()));

    }

    @GetMapping("/register")
    public ResponseEntity<?> registerInfo() {
        return ResponseEntity.ok(Map.of("info", "Use POST /register with JSON {name,email,password}"));
    }


}
