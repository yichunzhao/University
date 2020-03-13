package com.ynz.university.repo;


import com.ynz.university.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest  //spring boot integration test
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Transactional
    public void demoSimpleStudentCrudExample() {
        System.out.println("\n********Original Students *******");

        System.out.println("\n++++++++ show all students without enrolled courses, for it is lazy loaded. +++++");
        studentRepository.findAll().forEach(System.out::println);

        System.out.println("\n+++++++ show all student's enrolled courses. ++++++");
        studentRepository.findByAttendeeFirstNameAndAttendeeLastName("jane", "doe").getEnrolledCourses().forEach(System.out::println);

        //age up the students(age+=1)
        studentRepository.findAll().forEach(student -> {
            student.setAge(student.getAge() + 1);
            studentRepository.save(student);
        });

        System.out.println("\n********Students a year older*******");
        studentRepository.findAll().forEach(System.out::println);

        System.out.println("total student number: " + studentRepository.count());
    }

    @Test
    //@Transactional
    public void demoSpringMethodNameQuery() {
        System.out.println("total amount student: " + studentRepository.count());

        System.out.println("\n******age less than 20 year old:******* ");
        studentRepository.findByAgeLessThan(20).forEach(System.out::println);

        System.out.println("\n******age older than 21 year old:******* ");
        studentRepository.findByAgeGreaterThan(19).forEach(System.out::println);

        System.out.println("\n*****top 3 older than 15 year old:******");
        studentRepository.findTop3ByAgeGreaterThanOrderByAgeDesc(15).forEach(System.out::println);

        System.out.println("\n*****first person  order by ASC ******");
        studentRepository.findFirstByOrderByAttendeeLastNameAsc().forEach(System.out::println);

        System.out.println("\n****student part time");
        studentRepository.findByFullTimeFalse().forEach(System.out::println);

        System.out.println("\n****student full time");
        studentRepository.findByFullTimeTrue().forEach(System.out::println);

        System.out.println("\n****student last name like ki ");
        studentRepository.findByAttendeeLastNameLike("ki%").forEach(System.out::println);

        System.out.println("\n****find student by name ignoring case");
        studentRepository.findByAttendeeLastNameIgnoreCase("KIM").forEach(System.out::println);

        Student found = studentRepository.findByAttendeeFirstNameAndAttendeeLastName("jane", "doe");
        System.out.println("find by full name: " + found);

        System.out.println("\n****find full time student  ");
        studentRepository.findByFullTime(true).forEach(System.out::println);

    }


}