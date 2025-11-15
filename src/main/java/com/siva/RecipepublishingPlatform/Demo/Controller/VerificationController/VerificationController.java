package com.siva.RecipepublishingPlatform.Demo.Controller.VerificationController;


import com.siva.RecipepublishingPlatform.Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {


    @Autowired
    private UserService userService;


    @GetMapping("/verifyEmail")
    public ResponseEntity<?> verify(@RequestParam("token") String token) {
        String res = userService.verifyToken(token);
        if ("VERIFIED".equals(res)) return ResponseEntity.ok("Verified");
        if ("EXPIRED".equals(res)) return ResponseEntity.badRequest().body("Token expired");
        return ResponseEntity.badRequest().body("Invalid token");
    }
}
