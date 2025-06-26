package uz.dev.edusphere.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.dev.edusphere.dto.AssignmentDTO;
import uz.dev.edusphere.dto.CourseDTO;
import uz.dev.edusphere.dto.response.PageableDTO;
import uz.dev.edusphere.entity.Assignment;
import uz.dev.edusphere.entity.Course;
import uz.dev.edusphere.entity.User;
import uz.dev.edusphere.entity.template.AbsLongEntity;
import uz.dev.edusphere.mapper.AssignmentMapper;
import uz.dev.edusphere.mapper.CourseMapper;
import uz.dev.edusphere.repository.CourseRepository;
import uz.dev.edusphere.repository.UserRepository;
import uz.dev.edusphere.service.template.CourseService;
import uz.dev.edusphere.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 10:41
 **/

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    private final UserRepository userRepository;

    private final AssignmentMapper assignmentMapper;

    @Override
    public PageableDTO getAllCourses(Integer page) {

        Sort sort = Sort.by(AbsLongEntity.Fields.id).ascending();

        Pageable pageable = PageRequest.of(page, 10, sort);

        Page<Course> coursePage = courseRepository.findAll(pageable);

        List<Course> courses = coursePage.getContent();

        List<CourseDTO> courseDTOS = courseMapper.toDTO(courses);

        return new PageableDTO(
                coursePage.getSize(),
                coursePage.getTotalElements(),
                coursePage.getTotalPages(),
                !coursePage.isLast(),
                !coursePage.isFirst(),
                courseDTOS
        );

    }

    @Override
    public CourseDTO getCourseById(Long id) {

        Course course = courseRepository.findByIdOrThrowException(id);

        return courseMapper.toDTO(course);

    }

    @Override
    @Transactional
    public void createCourse(CourseDTO courseDTO) {

        Course course = courseMapper.toEntity(courseDTO);

        courseRepository.save(course);

    }

    @Override
    @Transactional
    public void updateCourse(Long id, CourseDTO courseDTO) {

        Course course = courseRepository.findByIdOrThrowException(id);

        Course updated = courseMapper.updateCourse(courseDTO, course);

        courseRepository.save(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Course course = courseRepository.findByIdOrThrowException(id);

        courseRepository.delete(course);

    }

    @Override
    @Transactional
    public void assigneeTeacher(Long courseId, Long teacherId) {

        Course course = courseRepository.findByIdOrThrowException(courseId);

        User teacher = userRepository.findByIdOrThrowException(teacherId);

        course.setTeacher(teacher);

        courseRepository.save(course);

    }

    @Override
    @Transactional
    public void addAssignee(Long courseId, AssignmentDTO assignmentDTO) {

        Course course = courseRepository.findByIdOrThrowException(courseId);

        Assignment assignment = assignmentMapper.toEntity(assignmentDTO);

        assignment.setCourse(course);

        List<Assignment> assignments = CommonUtil.getOrDefault(course.getAssignments(), new ArrayList<>());

        assignments.add(assignment);

        course.setAssignments(assignments);

        courseRepository.save(course);
    }
}
