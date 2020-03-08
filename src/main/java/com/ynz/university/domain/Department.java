package com.ynz.university.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Department")
@EqualsAndHashCode
@ToString
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "dept_id")
    private Integer id;

    @Column(name = "dept_name")
    @Size(max = 45)
    private String name;

    @OneToMany(mappedBy = "department", targetEntity = Course.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

}
