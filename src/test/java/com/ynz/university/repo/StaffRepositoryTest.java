package com.ynz.university.repo;

import com.ynz.university.domain.Staff;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StaffRepositoryTest {
    @Autowired
    private StaffRepository staffRepository;

    @Before
    public void setup() {
        System.out.println("+++++++++++++ start to run StaffRepositoryTest +++++++++++++ ");
    }

    @After
    public void cleanUp() {
        System.out.println("+++++++++++++ end of StaffRepositoryTest +++++++++++++ ");
    }

    @Test
    public void pagingAndSortingQueries(){
        Iterable<Staff> staffs = staffRepository.findAll(Sort.by(Sort.Direction.DESC,"personStaff.lastName"));
        staffs.forEach(System.out::println);
    }



}