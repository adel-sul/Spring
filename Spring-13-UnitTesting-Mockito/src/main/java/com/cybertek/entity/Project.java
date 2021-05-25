package com.cybertek.entity;

import com.cybertek.dto.UserDTO;
import com.cybertek.utils.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Project extends BaseEntity {

    private String projectName;

    @Column(unique = true) // won't let use same project code for different entities
    private String projectCode;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User assignedManager;

    private LocalDate startDate;
    private LocalDate endDate;
    private String projectDetail;

    @Enumerated(EnumType.STRING)
    private Status projectStatus;

}
