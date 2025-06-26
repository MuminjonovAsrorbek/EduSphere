package uz.dev.edusphere.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dev.edusphere.dto.GradeDTO;
import uz.dev.edusphere.service.template.SubmissionService;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 13:38
 **/

@RestController
@RequestMapping("/api/v1/submission")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping("/{submissionId}/grade")
    public ResponseEntity<?> addGradeBySubmissionId(@PathVariable Long submissionId,
                                                    @RequestBody @Valid GradeDTO gradeDTO) {

        submissionService.addGrade(submissionId, gradeDTO);

        return ResponseEntity.ok("Add Grade successfully");

    }

}
