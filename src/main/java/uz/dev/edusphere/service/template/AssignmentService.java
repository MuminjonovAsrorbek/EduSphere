package uz.dev.edusphere.service.template;

import uz.dev.edusphere.dto.SubmissionDTO;
import uz.dev.edusphere.dto.response.PageableDTO;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 13:10
 **/

public interface AssignmentService {

    PageableDTO getSubmissionByAssignmentId(Long assignmentId, Integer page);


    void addSubmit(Long assignmentId, SubmissionDTO submissionDTO);
}
