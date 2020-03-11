package com.ynz.university.repo;

import com.ynz.university.domain.Course;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Before
    public void setup() {
        System.out.println("+++++++++++++ start to run CourseRepositoryTest +++++++++++++ ");
    }

    @After
    public void cleanUp() {
        System.out.println("+++++++++++++ end of CourseRepositoryTest +++++++++++++ ");
    }

    @Test
    @Transactional
    public void showAllCourses() {
        System.out.println("++++++++++ all courses +++++++++++++");
        courseRepository.findAll().forEach(System.out::println);
    }

    @Test
    @Transactional
    public void testQueryByMethodName() {
        System.out.println("\n++++++ found course: ++++++ \n" + courseRepository.findByName("Chemistry"));

        System.out.println("\n++++++find courses by dane's last name+++++\n");
        courseRepository.findByDepartmentChairPersonStaffLastName("Jones").forEach(System.out::println);
    }

    @Test
    public void testCourseToString(){
        Course found = courseRepository.findByName("Chemistry");
        found.getCourses();
        found.getCourseStudents();
        found.getDepartment();
        found.toString();
    }

    @Test
    public void testJPQLQuery() {
        System.out.println("\n+++++++ get course view \n");
        System.out.println(courseRepository.getCourseViewByCourseId(22));
    }

    @Test
    @Transactional
    public void testPagingAndSortingQuery() {
        System.out.println("\n+++++++ find all 3-credit courses \n");
        List<Course> credit3 = courseRepository.findByCredits(3);
        assertNotNull(credit3);

        Page<Course> credit3Page = courseRepository.findByCredits(3, PageRequest.of(0, 3, Sort.Direction.ASC, "name"));
        assertThat(credit3Page.getSize(), is(3));

    }


}