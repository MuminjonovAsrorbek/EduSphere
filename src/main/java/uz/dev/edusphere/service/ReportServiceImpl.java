package uz.dev.edusphere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.dev.edusphere.exceptions.EntityNotFoundException;
import uz.dev.edusphere.projection.CoursePerformanceProjection;
import uz.dev.edusphere.repository.CourseRepository;
import uz.dev.edusphere.repository.GradeRepository;
import uz.dev.edusphere.service.template.ReportService;

import java.util.Optional;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 15:26
 **/

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final GradeRepository gradeRepository;

    private final CourseRepository courseRepository;


    @Override
    public CoursePerformanceProjection getCoursePerformance(Long courseId) {

        courseRepository.findByIdOrThrowException(courseId);

        Optional<CoursePerformanceProjection> coursePerformance = gradeRepository.getCoursePerformance(courseId);

        if (coursePerformance.isEmpty())
            throw new EntityNotFoundException("No grades available for this course.", HttpStatus.NOT_FOUND);

        return coursePerformance.get();

    }
}
