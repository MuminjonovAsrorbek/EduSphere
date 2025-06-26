package uz.dev.edusphere.service.template;

import uz.dev.edusphere.dto.GradeDTO;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 13:38
 **/

public interface SubmissionService {

    void addGrade(Long submissionId, GradeDTO gradeDTO);

}
