package uz.dev.edusphere.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.dev.edusphere.dto.AssignmentDTO;
import uz.dev.edusphere.dto.CourseDTO;
import uz.dev.edusphere.dto.response.PageableDTO;
import uz.dev.edusphere.service.template.CourseService;
import uz.dev.edusphere.service.template.LessonService;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 10:39
 **/

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    private final LessonService lessonService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PageableDTO getAllCourses(@RequestParam(required = false, defaultValue = "0", name = "page") Integer page) {

        return courseService.getAllCourses(page);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'TEACHER' , 'STUDENT')")
    public CourseDTO getCourseById(@PathVariable Long id) {

        return courseService.getCourseById(id);

    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCourse(@RequestBody @Valid CourseDTO courseDTO) {

        courseService.createCourse(courseDTO);

        return ResponseEntity.ok("Course created successfully");

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCourse(@PathVariable Long id,
                                          @RequestBody @Valid CourseDTO courseDTO) {

        courseService.updateCourse(id, courseDTO);

        return ResponseEntity.ok("Course updated successfully");

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {

        courseService.delete(id);

        return ResponseEntity.ok("Course deleted successfully");

    }

    @PostMapping("/{courseId}/assignee/{teacherId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assigneeTeacher(@PathVariable Long courseId,
                                             @PathVariable Long teacherId) {

        courseService.assigneeTeacher(courseId, teacherId);

        return ResponseEntity.ok("Course assigned successfully");
    }

    @GetMapping("/{courseId}/lessons")
    @PreAuthorize("hasAnyRole('ADMIN' , 'TEACHER' , 'STUDENT')")
    public PageableDTO getAllLessonByCourseId(@PathVariable Long courseId,
                                              @RequestParam(required = false, defaultValue = "0", name = "page") Integer page) {

        return lessonService.getAllLesson(page, courseId);

    }

    @PostMapping("/{courseId}/assignments")
    @PreAuthorize("@courseSecurity.canAddAssignee(#courseId)")
    public ResponseEntity<?> addAssignee(@PathVariable Long courseId,
                                         @RequestBody @Valid AssignmentDTO assignmentDTO) {

        courseService.addAssignee(courseId, assignmentDTO);

        return ResponseEntity.ok("Assignment created successfully");
    }

}
