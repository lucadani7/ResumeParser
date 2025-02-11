package com.lucadani.resumeparser.controller;

import com.lucadani.resumeparser.exceptions.DataNotFoundException;
import com.lucadani.resumeparser.model.Resume;
import com.lucadani.resumeparser.service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping
    public ResponseEntity<List<Resume>> getAllResumes() {
        return ResponseEntity.ok(resumeService.getAllResumes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resume> getResumeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(resumeService.getResumeById(id));
        } catch (DataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Resume> createResume(@RequestBody Resume resume) {
        return ResponseEntity.ok(resumeService.saveOrUpdateResume(resume));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resume> updateResume(@PathVariable Long id, @RequestBody Resume updatedResume) {
        if (Optional.ofNullable(resumeService.getResumeById(id)).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedResume.setId(id);
        return ResponseEntity.ok(resumeService.saveOrUpdateResume(updatedResume));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        try {
            resumeService.deleteResumeById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/job-title")
    public ResponseEntity<List<Resume>> searchByJobTitle(@RequestParam String keyword) {
        return ResponseEntity.ok(resumeService.searchByJobTitle(keyword));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Resume>> searchByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(resumeService.searchByJobOrContent(keyword));
    }
}
