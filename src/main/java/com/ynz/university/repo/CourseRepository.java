package com.ynz.university.repo;

import com.ynz.university.domain.Course;
import com.ynz.university.view.CourseView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    Course findByName(String name);

    //find course by chair last name
    List<Course> findByDepartmentChairPersonStaffLastName(String lastName);

    //create a course view
    @Query("select new com.ynz.university.view.CourseView(c.name,c.instructor.personStaff.lastName,c.department.name)" +
            "from Course c where c.id = ?1")
    CourseView getCourseViewByCourseId(int courseId);

    List<Course> findByCredits(int credits);

    //creating paging and sorting query method
    Page<Course> findByCredits(int credits, Pageable pageable);

    //find all with sorting
    List <Course> findAll(Sort sort);


}
