package uz.dev.edusphere.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import uz.dev.edusphere.entity.Enrollment;
import uz.dev.edusphere.entity.User;
import uz.dev.edusphere.exceptions.EntityNotFoundException;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    default Enrollment getEnrollmentByIdOrThrowException(Long id) {

        return findById(id).orElseThrow(() -> new EntityNotFoundException("Enrollment not found with ID :" + id, HttpStatus.NOT_FOUND));

    }

    Page<Enrollment> findAllByStudentId(Long id, Pageable pageable);


    List<Enrollment> findByStudent(User student);
}