package com.siva.RecipepublishingPlatform.Demo.VerificationToken;

import com.siva.RecipepublishingPlatform.Demo.Entity.Users;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "verification_tokens")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    private Users users;

    @Column(nullable = false)
    private Instant expiresAt;


    //default constructor
    public VerificationToken(){}

    //parameterized constructor
    public VerificationToken(String token, Users users, Instant expiresAt){
        this.token = token;
        this.users=users;
        this.expiresAt = expiresAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}


