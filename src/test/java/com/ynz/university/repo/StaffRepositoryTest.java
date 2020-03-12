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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Staff repository extends from PagingAndSortingRepository
 */

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
    public void demoJpaSortingQueries() {
        Iterable<Staff> staffs = staffRepository.findAll(Sort.by(Sort.Direction.DESC, "personStaff.lastName"));
        staffs.forEach(System.out::println);
    }

    @Test
    public void testJpaPagingSoringQueries() {
        Page<Staff> pageStaff = staffRepository.findAll(PageRequest.of(0, 4));
        assertNotNull(pageStaff);
        assertThat(pageStaff.getSize(), is(4));

        Page<Staff> pageStaffSorted = staffRepository.findAll(PageRequest.of(0, 4, Sort.Direction.ASC, "personStaff.lastName"));
        boolean match = pageStaffSorted.get().map(s->s.getPersonStaff().getLastName()).anyMatch(lastName -> lastName.startsWith("B"));
        assertTrue(match);
    }


}