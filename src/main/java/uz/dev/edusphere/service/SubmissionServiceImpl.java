package uz.dev.edusphere.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dev.edusphere.dto.GradeDTO;
import uz.dev.edusphere.entity.Grade;
import uz.dev.edusphere.entity.Submission;
import uz.dev.edusphere.enums.SubmissionStatus;
import uz.dev.edusphere.mapper.GradeMapper;
import uz.dev.edusphere.repository.SubmissionRepository;
import uz.dev.edusphere.service.template.SubmissionService;

import java.time.LocalDateTime;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 13:39
 **/

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;

    private final GradeMapper gradeMapper;

    @Override
    @Transactional
    public void addGrade(Long submissionId, GradeDTO gradeDTO) {

        Submission submission = submissionRepository.findByIdOrThrowException(submissionId);

        Grade grade = gradeMapper.toEntity(gradeDTO);

        grade.setSubmission(submission);
        grade.setGradedAt(LocalDateTime.now());

        submission.setGrade(grade);
        submission.setStatus(SubmissionStatus.GRADED);

        submissionRepository.save(submission);
    }
}
