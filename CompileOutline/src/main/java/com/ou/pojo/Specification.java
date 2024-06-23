package com.ou.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "specification")
@NamedQueries({
        @NamedQuery(name = "Specification.findAll", query = "select s from Specification s"),
        @NamedQuery(name = "Specification.findByOrderByIdDesc", query = "select s from Specification s order by s.id DESC"),
        @NamedQuery(name = "Specification.findByLecturerUser_Id", query = "select s from Specification s where s.lecturerUser.id = :id")
})

public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "credits", nullable = false)
    private Integer credits;

    @Column(name = "createdAt")
    private Instant createdAt;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "is_submitted")
    private Boolean isSubmitted;

    @Column(name = "assignment_date")
    private Instant assignmentDate;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Lecturer_User_id", nullable = false)
    @JsonIgnore
    private Lecturer lecturerUser;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Subject_id", nullable = false)
    @JsonIgnore
    private Subject subject;

    @OneToMany(mappedBy = "specification", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Objective> objectives = new LinkedHashSet<>();

    @OneToMany(mappedBy = "specification", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Outcome> outcomes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "specification", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<SpecificationRating> specificationRatings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "specification", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Feedback> feedbacks = new LinkedHashSet<>();
}
