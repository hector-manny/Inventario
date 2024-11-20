package com.example.ApiQuotations.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiQuotations.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
}