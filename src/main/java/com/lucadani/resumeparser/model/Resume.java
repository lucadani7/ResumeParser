package com.lucadani.resumeparser.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "resumes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Lob
    @Column(name = "resume_text", nullable = false)
    private String resumeText;
}
