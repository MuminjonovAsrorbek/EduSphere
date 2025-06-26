package uz.dev.edusphere.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import uz.dev.edusphere.entity.Submission;
import uz.dev.edusphere.exceptions.EntityNotFoundException;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    Page<Submission> findAllByAssignmentId(Long assignmentId, Pageable pageable);

    default Submission findByIdOrThrowException(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Submission not found with ID : " + id, HttpStatus.NOT_FOUND));
    }

}