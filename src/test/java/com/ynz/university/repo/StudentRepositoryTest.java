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

        studentRepository.deleteAll();
        System.out.println("\n********Students removed********");
        System.out.println("total student number: " + studentRepository.count());
    }


}