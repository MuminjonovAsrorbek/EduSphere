package uz.dev.edusphere.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import uz.dev.edusphere.entity.Lesson;
import uz.dev.edusphere.exceptions.EntityNotFoundException;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Page<Lesson> findAllByCourseId(Long courseId, Pageable pageable);

    default Lesson findByIdOrThrowException(Long id) {

        return findById(id).orElseThrow(() -> new EntityNotFoundException("Lesson not found with ID : " + id, HttpStatus.NOT_FOUND));

    }


}