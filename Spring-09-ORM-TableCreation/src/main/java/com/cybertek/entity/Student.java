package com.cybertek.entity;

import com.cybertek.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autogenerate ids
    private Long id;

    @Column(name = "studentFirstName") // used to give column a custom name
    private String firstName;

    @Column(name = "studentLastName")
    private String lastName;
    private String email;

    @Transient // field will not be mapped and column won't be created in DB
    private String city;

    @Temporal(TemporalType.DATE) // for dateTime type columns
    private Date birthDate;
    @Temporal(TemporalType.TIME)
    private Date birthTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDateTime;

    @Column(columnDefinition = "DATE") // Java 8 version
    private LocalDate localDate;
    @Column(columnDefinition = "TIME")
    private LocalTime localTime;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING) // for enums. EnumType.ORDINAL - 0, 1
    private Gender gender;
}
