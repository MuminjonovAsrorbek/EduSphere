package uz.dev.edusphere.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.dev.edusphere.dto.AssignmentDTO;
import uz.dev.edusphere.dto.SubmissionDTO;
import uz.dev.edusphere.dto.response.PageableDTO;
import uz.dev.edusphere.service.template.AssignmentService;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 13:07
 **/

@RestController
@RequestMapping("/api/v1/assignment")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping("/{assignmentId}")
    @PreAuthorize("@courseSecurity.canViewSubmission(#assignmentId)")
    public PageableDTO getSubmissionByAssignmentId(@PathVariable Long assignmentId,
                                                   @RequestParam(required = false, defaultValue = "0", name = "page") Integer page) {

        return assignmentService.getSubmissionByAssignmentId(assignmentId, page);

    }

    @PreAuthorize("@courseSecurity.canAssignmentSubmit(#assignmentId)")
    @PostMapping("/{assignmentId}/submit")
    public ResponseEntity<?> addSubmit(@PathVariable Long assignmentId,
                                       @RequestBody @Valid SubmissionDTO submissionDTO) {

        assignmentService.addSubmit(assignmentId, submissionDTO);

        return ResponseEntity.ok("Add submit successfully");

    }

}
