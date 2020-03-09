package com.ynz.university.repo;

import com.ynz.university.domain.Staff;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<Staff, Integer> {
}
