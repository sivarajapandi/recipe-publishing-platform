package com.siva.RecipepublishingPlatform.Demo.Repository;

//import com.siva.RecipepublishingPlatform.Demo.Entity.User;
import com.siva.RecipepublishingPlatform.Demo.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Users findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String Email);






}
