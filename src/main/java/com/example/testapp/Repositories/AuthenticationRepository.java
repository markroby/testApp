package com.example.testapp.Repositories;

import com.example.testapp.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthenticationRepository extends JpaRepository<Users, Long> {

    Users findByUsernameOrEmail(String username, String email);
}
