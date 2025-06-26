package uz.dev.edusphere.service.template;

import uz.dev.edusphere.dto.EnrollmentDTO;
import uz.dev.edusphere.dto.response.PageableDTO;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 11:58
 **/

public interface EnrollmentService {

    void create(EnrollmentDTO enrollmentDTO);

    void delete(Long id);

    PageableDTO getMyCourses(Integer page);

}
