package com.example.ApiQuotations.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiQuotations.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}