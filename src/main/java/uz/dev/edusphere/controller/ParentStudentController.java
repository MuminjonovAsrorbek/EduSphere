package uz.dev.edusphere.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.dev.edusphere.dto.ParentStudentLinkDTO;
import uz.dev.edusphere.dto.response.PageableDTO;
import uz.dev.edusphere.service.template.ParentStudentService;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 00:22
 **/

@RestController
@RequestMapping("/api/v1/parent-student")
@RequiredArgsConstructor
public class ParentStudentController {

    private final ParentStudentService parentStudentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody @Valid ParentStudentLinkDTO dto) {

        parentStudentService.create(dto);

        return ResponseEntity.ok("ParentStudentLink created successfully");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        parentStudentService.delete(id);

        return ResponseEntity.ok("ParentStudentLink deleted successfully");

    }

    @GetMapping("/{childrenId}/grades")
    @PreAuthorize("@courseSecurity.canViewMyChildrenGrades(#childrenId)")
    public PageableDTO getMyChildrenGrades(@PathVariable Long childrenId,
                                           @RequestParam(required = false, defaultValue = "0", name = "page") Integer page) {

        return parentStudentService.getMyChildrenGrades(childrenId, page);

    }
}
