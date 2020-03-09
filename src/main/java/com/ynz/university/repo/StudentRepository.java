package com.ynz.university.repo;

import com.ynz.university.domain.Person;
import com.ynz.university.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    //Simple Query Methods(return type findBy Entity attribute(attribute data tpe attribute name))
    List<Student> findByFullTime(boolean fullTime);
    List<Student> findByFullTimeTrue();
    List<Student> findByFullTimeFalse();

    List<Student> findByAge(int age);

    List<Student> findByAttendeeLastName(String lastName);

    //SQL AND, OR and NOT operators
    Student findByAttendeeFirstNameAndAttendeeLastName(String firstName, String lastName);
    Student findByAttendee(Person person);

    List<Student> findByAgeGreaterThan(int minAge);
    List<Student> findByAgeLessThan(int maxAge);
    List<Student> findByAttendeeLastNameIgnoreCase(String lastName);

    List<Student> findByAttendeeLastNameLike(String likeString);
    List<Student> findByAttendeeLastNameNotLike(String likeString);

    //order by clause
    //With Spring Data JPA, you just need to add the words OrderBy to your query followed by the name of
    // the entity attribute and the abbreviations ASC or DESC for your preferred order.
    List<Student> findByAttendeeLastNameOrderByLastNameAsc(String lastName);

    //Limiting the number of results
    //With Spring Data JPA, you can do the same by adding the keywords Top or First followed by a number between the find and By keywords.
    List<Student> findTop3ByAgeGreaterThanOrderByAgeDesc(int minAge);
    Student findTopByOrderByAgeDesc();

    //Paginate the results
    // Page<T> findAll(Pageable pageable)


}
