package com.capricon.Authentication_System.repo;

import com.capricon.Authentication_System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

    //all JpaRepo methods will be used for CRUD operations

    //Custom method to find User by email
    User findByEmail(String email);

}
