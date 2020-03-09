package com.ynz.university.repo;

import com.ynz.university.domain.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    @After
    public void setup() {
        System.out.println("+++++++++++++ run jpa repository ++++++++++++++");
    }

    @Test
    @Transactional
    public void runJpaRepositoryMethods() {
        departmentRepository.save(new Department("Humanities"));
        //flush all pending changes into database. (persisting changes of persistence context into db)
        departmentRepository.flush();

        departmentRepository.saveAndFlush(new Department("Fine Arts"));

        System.out.println("\n************3 Departments****************");
        departmentRepository.findAll().forEach(System.out::println);

        departmentRepository.deleteInBatch(departmentRepository.findAll().subList(0, 1));//fromIndex(Inclusive),toIndex(exclusive)
        System.out.println("count : " + departmentRepository.count());
        System.out.println("\n********* what left **********");
        departmentRepository.findAll().forEach(System.out::println);

        departmentRepository.deleteAllInBatch();
        System.out.println("\n********* nothing left **********");
        departmentRepository.findAll().forEach(System.out::println);
        System.out.println("count : " + departmentRepository.count());
    }

    @Test
    public void gettingPersistenceContextInjected(){
        assertNotNull(entityManager);
    }

}