package uz.dev.edusphere.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.dev.edusphere.dto.LessonDTO;
import uz.dev.edusphere.dto.response.PageableDTO;
import uz.dev.edusphere.entity.Course;
import uz.dev.edusphere.entity.Lesson;
import uz.dev.edusphere.entity.template.AbsLongEntity;
import uz.dev.edusphere.mapper.LessonMapper;
import uz.dev.edusphere.repository.CourseRepository;
import uz.dev.edusphere.repository.LessonRepository;
import uz.dev.edusphere.service.template.LessonService;

import java.util.List;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 12:13
 **/

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    private final CourseRepository courseRepository;

    private final LessonMapper lessonMapper;

    @Override
    public PageableDTO getAllLesson(Integer page, Long courseId) {

        Sort sort = Sort.by(AbsLongEntity.Fields.id).ascending();

        Pageable pageable = PageRequest.of(page, 10, sort);

        Page<Lesson> listPage = lessonRepository.findAllByCourseId(courseId, pageable);

        List<Lesson> lessons = listPage.getContent();

        List<LessonDTO> lessonDTOS = lessonMapper.toDTO(lessons);

        return new PageableDTO(
                listPage.getSize(),
                listPage.getTotalElements(),
                listPage.getTotalPages(),
                !listPage.isLast(),
                !listPage.isFirst(),
                lessonDTOS
        );
    }

    @Override
    public LessonDTO getLessonById(Long id) {

        Lesson lesson = lessonRepository.findByIdOrThrowException(id);

        return lessonMapper.toDTO(lesson);

    }

    @Override
    @Transactional
    public void createLesson(LessonDTO lessonDTO) {

        Course course = courseRepository.findByIdOrThrowException(lessonDTO.getCourseId());

        Lesson lesson = lessonMapper.toEntity(lessonDTO);

        lesson.setCourse(course);

        lessonRepository.save(lesson);

    }

    @Override
    @Transactional
    public void updateLesson(Long id, LessonDTO lessonDTO) {

        Lesson lesson = lessonRepository.findByIdOrThrowException(id);

        Lesson updatedLesson = lessonMapper.updateLesson(lessonDTO, lesson);

        Course course = courseRepository.findByIdOrThrowException(lessonDTO.getCourseId());

        updatedLesson.setCourse(course);

        lessonRepository.save(updatedLesson);

    }

    @Override
    @Transactional
    public void deleteLesson(Long id) {

        Lesson lesson = lessonRepository.findByIdOrThrowException(id);

        lessonRepository.delete(lesson);

    }
}
