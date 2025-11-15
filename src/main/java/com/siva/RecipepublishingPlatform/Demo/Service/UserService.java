package com.siva.RecipepublishingPlatform.Demo.Service;

//import com.siva.RecipepublishingPlatform.Demo.Entity.User;
import com.siva.RecipepublishingPlatform.Demo.Entity.Roles;
import com.siva.RecipepublishingPlatform.Demo.Entity.UserDto;
import com.siva.RecipepublishingPlatform.Demo.Entity.Users;
import com.siva.RecipepublishingPlatform.Demo.Repository.UserRepository;
import com.siva.RecipepublishingPlatform.Demo.Repository.VerificationTokenRepository;
import com.siva.RecipepublishingPlatform.Demo.VerificationToken.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${app.auth.emailVerification:true}")
    private boolean emailVerificationEnabled;

    //here we should not save the password as plain text
    //we should hash the password before saving it to the database
    //first lets check how te password is being saved

    // we need to implement the registerUser method
    public Users registerUser(UserDto user) throws IllegalArgumentException{
        if (userRepository.existsByUsername(user.getName())) {
            throw new IllegalArgumentException("username taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("email already used");
        }
        Users newUser = new Users();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        //hash the password before saving it to the database
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.setRoles(Roles.USER);
        newUser.setIsenabled(!emailVerificationEnabled);

        Users saved = userRepository.save(newUser);

        if(emailVerificationEnabled){
            createAndSendVerificationToken(saved);
        }
        return saved;
    }

    public void createAndSendVerificationToken(Users user){
        //create a verification token
        String token = java.util.UUID.randomUUID().toString();
        VerificationToken v = new VerificationToken(token, user, Instant.now().plusSeconds(60*60*24)); // 24h
        verificationTokenRepository.save(v);

        //send the verification email
        //for now we will just print the token to the console
        System.out.println("Verification token for user " + user.getEmail() + ": " + token);
    }

    public String verifyToken(String token) {
        VerificationToken v = verificationTokenRepository.findByToken(token);
        if (v == null) return "INVALID";
        if (v.getExpiresAt().isBefore(Instant.now())) return "EXPIRED";
        Users user = v.getUsers();
        user.setIsenabled(true);
        userRepository.save(user);
        verificationTokenRepository.delete(v);
        return "VERIFIED";
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users registeredUser = userRepository.findByUsername(username);
        if (registeredUser == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(registeredUser.getName())
                .password(registeredUser.getPassword())
                .roles(registeredUser.getRoles().name())
                .build();
    }


}
