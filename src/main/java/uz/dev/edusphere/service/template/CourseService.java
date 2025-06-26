package uz.dev.edusphere.service.template;

import uz.dev.edusphere.dto.AssignmentDTO;
import uz.dev.edusphere.dto.CourseDTO;
import uz.dev.edusphere.dto.response.PageableDTO;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 10:41
 **/

public interface CourseService {

    PageableDTO getAllCourses(Integer page);

    CourseDTO getCourseById(Long id);

    void createCourse(CourseDTO courseDTO);

    void updateCourse(Long id, CourseDTO courseDTO);

    void delete(Long id);

    void assigneeTeacher(Long courseId, Long teacherId);

    void addAssignee(Long courseId , AssignmentDTO assignmentDTO);

}
