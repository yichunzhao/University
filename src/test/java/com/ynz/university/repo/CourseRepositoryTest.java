package com.ynz.university.repo;

import com.ynz.university.domain.Course;
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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Before
    public void setup() {
        System.out.println("+++++++++++++ start to run CourseRepositoryTest +++++++++++++ ");
    }

    @After
    public void cleanUp() {
        System.out.println("+++++++++++++ end of CourseRepositoryTest +++++++++++++ ");
    }

    @Test
    public void showAllCourses() {
        System.out.println("\n++++++++++ all courses +++++++++++++");
        courseRepository.findAll().forEach(System.out::println);
        System.out.println("\n++++++++++ end of all courses +++++++++++++");
    }

    @Test
    public void testQueryByMethodName() {
        System.out.println("\n++++++find courses by dane's last name+++++\n");

        List<Course> courses = courseRepository.findByDepartmentChairPersonStaffLastName("Jones");
        assertNotNull(courses);

        System.out.println("\n++++++end of finding courses by dane's last name+++++\n");
    }

    @Test
    public void testCourseToString() {
        Course found = courseRepository.findByName("Chemistry");
        assertThat(found.getName(), is("Chemistry"));
    }

    @Test
    public void testJPQLQuery() {
        System.out.println("\n+++++++ get course view \n");
        System.out.println(courseRepository.getCourseViewByCourseId(22));
    }

    @Test
    public void testFindAllCoursesHavingSpecificCredit() {
        System.out.println("\n+++++++ find all 3-credit courses \n");
        List<Course> credit3 = courseRepository.findByCredits(3);
        assertNotNull(credit3);
    }

    @Test
    public void testQueryAllWithSorting() {
        System.out.println("..............testQueryAllWithSorting .............");
        courseRepository.findAll().forEach(System.out::println);

        System.out.println("..............with sorting .............");

        List<Course> found = courseRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        assertThat(found.get(0).getName(), startsWith("A"));

        System.out.println("..............End of testQueryAllWithSorting .............");
    }

    @Test
    public void testQueryAllCredit3WithPaging() {
        Page<Course> page = courseRepository.findByCredits(3, PageRequest.of(0, 3, Sort.Direction.ASC, "name"));
        assertThat(page.getContent().size(), is(3));

        boolean b = page.get().map(course -> course.getName()).anyMatch(n -> n.startsWith("A"));
        assertThat(b, is(true));
    }

    @Test
    public void testEagerFetchedDepartmentCourses() {
        List<Course> found = courseRepository.findByCredits(3);
        found.stream().map(f -> f.getDepartment().getCourses()).forEach(c -> assertNotNull(c));
    }

}