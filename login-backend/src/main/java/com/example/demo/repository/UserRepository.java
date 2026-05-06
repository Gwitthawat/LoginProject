package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Usermodel;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<Usermodel,Long>{
    Optional<Usermodel> findByUsername(String username);
    boolean existsByUsername(String username);
}
