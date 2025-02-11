package com.lucadani.resumeparser.repository;

import com.lucadani.resumeparser.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    // Find resumes by job title containing a keyword (case-insensitive)
    List<Resume> findByJobTitleContainingIgnoreCase(String keyword);

    // Custom query to find resumes by job title and resume content
    List<Resume> findByJobTitleContainingIgnoreCaseOrResumeTextContainingIgnoreCase(String jobKeyword, String textKeyword);
}
