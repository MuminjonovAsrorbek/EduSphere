package uz.dev.edusphere.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.dev.edusphere.dto.EnrollmentDTO;
import uz.dev.edusphere.dto.response.PageableDTO;
import uz.dev.edusphere.service.template.EnrollmentService;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 11:56
 **/

@RestController
@RequestMapping("/api/v1/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody @Valid EnrollmentDTO enrollmentDTO) {

        enrollmentService.create(enrollmentDTO);

        return ResponseEntity.ok("Enrollment created successfully");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        enrollmentService.delete(id);

        return ResponseEntity.ok("Enrollment deleted successfully");
    }

    @GetMapping("/my/courses")
    @PreAuthorize("hasAnyRole('ADMIN' , 'STUDENT')")
    public PageableDTO getMyCourses(@RequestParam(required = false, defaultValue = "0", name = "page") Integer page) {

        return enrollmentService.getMyCourses(page);

    }
}
