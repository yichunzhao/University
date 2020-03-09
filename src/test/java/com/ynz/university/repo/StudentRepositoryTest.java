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
    public void simpleStudentCrudExample() {
        studentRepository.save(new Student("jane", "Abram", true, 20));
        studentRepository.save(new Student("john", "Kent", true, 22));
        studentRepository.save(new Student("mike", "Landon", true, 19));
        studentRepository.save(new Student("ally", "Harley", false, 18));

        System.out.println("\n********Original Students *******");
        studentRepository.findAll().forEach(e -> System.out.println(e));

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
    @Transactional
    public void usingSpringMethodNameQuery(){
        System.out.println("total amount student: " + studentRepository.count());

        System.out.println("\n******age less than 20 year old:******* ");
        studentRepository.findByAgeLessThan(20).forEach(System.out::println);

        System.out.println("\n******age older than 21 year old:******* ");
        studentRepository.findByAgeGreaterThan(21).forEach(System.out::println);

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

        System.out.println("\n****find full time student  ");
        studentRepository.findByFullTime(true).forEach(System.out::println);

    }


}