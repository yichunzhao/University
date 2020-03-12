package com.ynz.university.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Staff_Table")
@EqualsAndHashCode
@NoArgsConstructor
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

    public Staff(Person person) {
        this.personStaff = person;
    }
}
