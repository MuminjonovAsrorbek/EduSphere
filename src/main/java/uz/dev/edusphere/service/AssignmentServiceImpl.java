package uz.dev.edusphere.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.dev.edusphere.dto.SubmissionDTO;
import uz.dev.edusphere.dto.response.PageableDTO;
import uz.dev.edusphere.entity.Assignment;
import uz.dev.edusphere.entity.Submission;
import uz.dev.edusphere.entity.User;
import uz.dev.edusphere.entity.template.AbsLongEntity;
import uz.dev.edusphere.enums.SubmissionStatus;
import uz.dev.edusphere.exceptions.DateTimeExpiredException;
import uz.dev.edusphere.mapper.SubmissionMapper;
import uz.dev.edusphere.repository.AssignmentRepository;
import uz.dev.edusphere.repository.SubmissionRepository;
import uz.dev.edusphere.service.template.AssignmentService;
import uz.dev.edusphere.utils.SecurityUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 13:10
 **/

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private final SubmissionRepository submissionRepository;

    private final SubmissionMapper submissionMapper;

    private final SecurityUtils securityUtils;

    @Override
    public PageableDTO getSubmissionByAssignmentId(Long assignmentId, Integer page) {

        Sort sort = Sort.by(AbsLongEntity.Fields.id).ascending();

        Pageable pageable = PageRequest.of(page, 10, sort);

        Page<Submission> submissionPage = submissionRepository.findAllByAssignmentId(assignmentId, pageable);

        List<Submission> submissions = submissionPage.getContent();

        List<SubmissionDTO> submissionDTOS = submissionMapper.toDTO(submissions);

        return new PageableDTO(
                submissionPage.getSize(),
                submissionPage.getTotalElements(),
                submissionPage.getTotalPages(),
                !submissionPage.isLast(),
                !submissionPage.isFirst(),
                submissionDTOS
        );
    }

    @Override
    @Transactional
    public void addSubmit(Long assignmentId, SubmissionDTO submissionDTO) {

        Assignment assignment = assignmentRepository.findByIdOrThrowException(assignmentId);

        if (assignment.getDeadline().isAfter(LocalDateTime.now())) {

            throw new DateTimeExpiredException("The deadline time expired", HttpStatus.BAD_REQUEST);

        }

        Submission submission = new Submission();

        User currentUser = securityUtils.getCurrentUser();

        submission.setContent(submissionDTO.getContent());
        submission.setAssignment(assignment);
        submission.setStudent(currentUser);
        submission.setSubmittedAt(LocalDateTime.now());
        submission.setStatus(SubmissionStatus.PENDING_REVIEW);

        submissionRepository.save(submission);

    }
}
