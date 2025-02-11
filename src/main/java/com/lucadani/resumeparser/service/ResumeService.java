package com.lucadani.resumeparser.service;

import com.lucadani.resumeparser.exceptions.DataNotFoundException;
import com.lucadani.resumeparser.model.Resume;
import com.lucadani.resumeparser.repository.ResumeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    public Resume getResumeById(Long id) {
        String message = String.format("The resume with id %s does not exist!", id);
        return resumeRepository.findById(id).orElseThrow(() -> new DataNotFoundException(message));
    }

    public Resume saveOrUpdateResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    public void deleteResumeById(Long id) {
        if (!resumeRepository.existsById(id)) {
            throw new DataNotFoundException(String.format("The resume with id %s does not exist!", id));
        }
        resumeRepository.deleteById(id);
    }

    public List<Resume> searchByJobTitle(String jobTitle) {
        return resumeRepository.findByJobTitleContainingIgnoreCase(jobTitle);
    }

    public List<Resume> searchByJobOrContent(String keyword) {
        return resumeRepository.findByJobTitleContainingIgnoreCaseOrResumeTextContainingIgnoreCase(keyword, keyword);
    }
}
