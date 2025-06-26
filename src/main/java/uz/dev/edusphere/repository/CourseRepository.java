package uz.dev.edusphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import uz.dev.edusphere.entity.Course;
import uz.dev.edusphere.exceptions.EntityNotFoundException;

public interface CourseRepository extends JpaRepository<Course, Long> {

    default Course findByIdOrThrowException(Long id) {

        return findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Course not found with ID : " + id, HttpStatus.NOT_FOUND)
                );

    }

}