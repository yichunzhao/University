package com.ynz.university.domain;

import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Department")
@ToString(exclude = "courses")
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "dept_id")
    private Integer id;

    @Column(name = "dept_name")
    @Size(max = 45)
    private String name;

    @OneToMany(mappedBy = "department", targetEntity = Course.class)
    private Set<Course> courses = new HashSet<>();

    //uni-directional
    @OneToOne
    @JoinColumn(name = "staff_id_fk", referencedColumnName = "staff_id")
    private Staff chair;

    public Department() {
    }

    public Department(@Size(max = 45) String name) {
        this.name = name;
    }

    public Department(@Size(max = 45) String name, Staff chair) {
        this.name = name;
        this.chair = chair;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Staff getChair() {
        return chair;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(Course course) {
        this.courses.add(course);
    }

    public void setChair(Staff chair) {
        this.chair = chair;
    }

}
