package com.ctwokm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctwokm.pojo.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}