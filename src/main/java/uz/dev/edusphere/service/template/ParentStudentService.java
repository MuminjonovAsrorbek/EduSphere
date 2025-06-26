package uz.dev.edusphere.service.template;

import uz.dev.edusphere.dto.ParentStudentLinkDTO;
import uz.dev.edusphere.dto.response.PageableDTO;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 10:31
 **/

public interface ParentStudentService {

    void create(ParentStudentLinkDTO dto);

    void delete(Long id);

    PageableDTO getMyChildrenGrades(Long childrenId, Integer page);
}
