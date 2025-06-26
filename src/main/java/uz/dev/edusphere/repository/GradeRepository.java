package uz.dev.edusphere.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.dev.edusphere.entity.Grade;
import uz.dev.edusphere.projection.CoursePerformanceProjection;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    Page<Grade> findAllBySubmissionStudentId(Long studentId, Pageable pageable);

    @Query("SELECT c.id as courseId, c.name as courseName, AVG(g.score) as averageScore " +
            "FROM Grade g " +
            "JOIN g.submission s " +
            "JOIN s.assignment a " +
            "JOIN a.course c " +
            "WHERE c.id = :courseId " +
            "GROUP BY c.id, c.name")
    Optional<CoursePerformanceProjection> getCoursePerformance(@Param("courseId") Long courseId);


}