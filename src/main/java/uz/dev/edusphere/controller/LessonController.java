package uz.dev.edusphere.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.dev.edusphere.dto.LessonDTO;
import uz.dev.edusphere.service.template.LessonService;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 12:10
 **/

@RestController
@RequestMapping("/api/v1/lesson")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'TEACHER' , 'STUDENT')")
    public LessonDTO getLessonById(@PathVariable Long id) {

        return lessonService.getLessonById(id);

    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN' ,'TEACHER')")
    public ResponseEntity<?> createLesson(@RequestBody @Valid LessonDTO lessonDTO) {

        lessonService.createLesson(lessonDTO);

        return ResponseEntity.ok("Lesson created successfully");

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' ,'TEACHER')")
    public ResponseEntity<?> updateLesson(@PathVariable Long id,
                                          @RequestBody @Valid LessonDTO lessonDTO) {

        lessonService.updateLesson(id, lessonDTO);

        return ResponseEntity.ok("Lesson updated successfully");

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteLesson(@PathVariable Long id) {

        lessonService.deleteLesson(id);

        return ResponseEntity.ok("Lesson deleted successfully");
    }
}
