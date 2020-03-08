package com.ynz.university.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable //stored as an intrinsic part of an entity
@Getter
@ToString
public class Person {
    @Column
    private String firstName;

    @Column
    private String lastName;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
