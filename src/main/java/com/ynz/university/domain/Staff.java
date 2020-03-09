package com.ynz.university.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Staff_Table")
@EqualsAndHashCode
@ToString
public class Staff {
    @Id
    @GeneratedValue
    @Column(name = "staff_id")
    @Getter
    private Integer staffId;

    @Embedded
    @Getter
    @Setter
    private Person personStaff;

    public Staff() {
    }

    public Staff(Person person) {
        this.personStaff = person;
    }
}
