package com.siva.RecipepublishingPlatform.Demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users", indexes = {
        @Index(name ="idx_users_handle", columnList = "handle")})
public class  Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles roles;

    @Column (unique = true)
    private String handle;

    @Column (nullable = false)
    private boolean isenabled;



    public Users(){

    }

    //this constructor is used when creating a new user
    public Users(long id, String name, String email, String Password){
        this.id = id;
        this.username = name;
        this.email = email;
        this.password = password;
        this.roles = Roles.USER;
        this.handle = null;

    }

    //constructor used when creating the chef user with the handle Generation
    public Users(long id, String name, String email, String password, Roles roles){
        this.id = id;
        this.username = name;
        this.email = email;
        this.password = password;
        this.roles = Roles.CHEF;
        this.handle = generateUserHandle();
    }

    //constructor used when creating admin user
    public Users(long id, String name, String email, String password, Roles roles, String handle){
            this.id = id;
            this.username = name;
            this.email = email;
            this.password = password;
            this.roles = Roles.ADMIN;
            this.handle = null;
        }




    private String generateUserHandle() {
        return "CHEF" + UUID.randomUUID().toString();
    }



    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password){
        this.password = password;
    }

    public void setRoles(Roles roles){
        this.roles = roles;
    }

    public Roles getRoles(){
        return roles;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public boolean isIsenabled() {
        return isenabled;
    }

    public void setIsenabled(boolean isenabled) {
        this.isenabled = isenabled;
    }
}
