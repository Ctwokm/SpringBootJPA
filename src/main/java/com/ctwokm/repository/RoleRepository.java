package com.ctwokm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctwokm.pojo.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}