package uz.dev.edusphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import uz.dev.edusphere.entity.Assignment;
import uz.dev.edusphere.exceptions.EntityNotFoundException;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    default Assignment findByIdOrThrowException(Long assignmentId) {

        return findById(assignmentId).orElseThrow(() -> new EntityNotFoundException("Assignment not found with ID : " + assignmentId, HttpStatus.NOT_FOUND));

    }


}