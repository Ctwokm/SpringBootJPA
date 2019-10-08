package com.ctwokm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctwokm.pojo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
    User findByName(String name);
}