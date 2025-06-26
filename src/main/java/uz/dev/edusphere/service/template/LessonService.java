package uz.dev.edusphere.service.template;

import uz.dev.edusphere.dto.LessonDTO;
import uz.dev.edusphere.dto.response.PageableDTO;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 12:13
 **/

public interface LessonService {

    PageableDTO getAllLesson(Integer page, Long courseId);

    LessonDTO getLessonById(Long id);

    void createLesson(LessonDTO lessonDTO);

    void updateLesson(Long id, LessonDTO lessonDTO);

    void deleteLesson(Long id);

}
