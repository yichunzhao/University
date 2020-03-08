package com.ynz.university.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//Plain old java object(POJO)
@Entity
@Table(name = "Course")
@EqualsAndHashCode
@ToString
public class Course {
    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private Integer id;

    @Column(name = "course_name")
    @Size(max = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "course_dept_id")
    private Department department;

    @ManyToMany(mappedBy ="courses")
    private Set<Student> courseStudents = new HashSet<>();

    public Course() {
    }

    public Course(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void addOneStudent(Student student) {
        Optional.ofNullable(student).ifPresent(s -> this.courseStudents.add(s));
    }

    public Set<Student> getCourseStudents() {
        return courseStudents;
    }
}
