package com.ynz.university.repo;

import com.ynz.university.domain.Staff;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StaffRepository extends PagingAndSortingRepository<Staff, Integer> {
}
