package com.pegasus.odontosys.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.pegasus.odontosys.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<UserDetails> findUserByEmail(String email);

}
