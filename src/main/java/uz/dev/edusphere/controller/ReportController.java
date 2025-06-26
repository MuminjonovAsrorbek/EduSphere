package uz.dev.edusphere.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dev.edusphere.projection.CoursePerformanceProjection;
import uz.dev.edusphere.service.template.ReportService;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 15:25
 **/

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/courses/{courseId}/performance")
    @PreAuthorize("hasRole('ADMIN')")
    public CoursePerformanceProjection getCoursePerformance(@PathVariable Long courseId) {

        return reportService.getCoursePerformance(courseId);

    }

}
