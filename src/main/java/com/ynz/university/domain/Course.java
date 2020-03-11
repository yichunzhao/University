package com.ynz.university.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Course")
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private Integer id;

    @Column(name = "course_name")
    private String name;

    @Column
    private int credits;

    @ManyToOne
    @JoinColumn(name = "course_dept_id")
    private Department department;

    @ManyToMany(mappedBy = "studentCourses", targetEntity = Student.class, fetch = FetchType.EAGER)
    private Set<Student> courseStudents = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "staff_id_fk", referencedColumnName = "staff_id")
    private Staff instructor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "course_course",
            joinColumns = @JoinColumn(name = "course_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id_fk")
    )
    private Set<Course> prerequisites = new HashSet<>();

    @ManyToMany(mappedBy = "prerequisites", fetch = FetchType.EAGER)
    private Set<Course> courses = new HashSet<>();

    public Course(String name, Integer credits, Staff instructor, Department department) {
        this.name = name;
        this.credits = credits;
        this.instructor = instructor;
        this.department = department;
    }

    public Course addPrerequisite(Course prerequisite) {
        prerequisites.add(prerequisite);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public Set<Course> getPrerequisites() {
        return prerequisites;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Department getDepartment() {
        return department;
    }

    public Set<Student> getCourseStudents() {
        return courseStudents;
    }

    public Staff getInstructor() {
        return instructor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setPrerequisites(Set<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setCourseStudents(Set<Student> courseStudents) {
        this.courseStudents = courseStudents;
    }

    public void setInstructor(Staff instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", prerequisites=" + prerequisites +
                ", department=" + department +
                '}';
    }


}
