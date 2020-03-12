package com.ynz.university.repo;

import com.ynz.university.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository has two features
 * 1. query by paging and sorting
 * 2. query by Example.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
