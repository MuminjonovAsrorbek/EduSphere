package uz.dev.edusphere.service.template;

import uz.dev.edusphere.projection.CoursePerformanceProjection;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 15:26
 **/

public interface ReportService {
    CoursePerformanceProjection getCoursePerformance(Long courseId);

}
