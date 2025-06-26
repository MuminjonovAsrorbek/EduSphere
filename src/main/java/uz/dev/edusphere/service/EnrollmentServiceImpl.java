package uz.dev.edusphere.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.dev.edusphere.dto.CourseDTO;
import uz.dev.edusphere.dto.EnrollmentDTO;
import uz.dev.edusphere.dto.response.PageableDTO;
import uz.dev.edusphere.entity.Course;
import uz.dev.edusphere.entity.Enrollment;
import uz.dev.edusphere.entity.User;
import uz.dev.edusphere.entity.template.AbsLongEntity;
import uz.dev.edusphere.mapper.CourseMapper;
import uz.dev.edusphere.mapper.EnrollmentMapper;
import uz.dev.edusphere.repository.CourseRepository;
import uz.dev.edusphere.repository.EnrollmentRepository;
import uz.dev.edusphere.repository.UserRepository;
import uz.dev.edusphere.service.template.EnrollmentService;
import uz.dev.edusphere.utils.SecurityUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 11:58
 **/

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final EnrollmentMapper enrollmentMapper;

    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    private final SecurityUtils securityUtils;

    private final CourseMapper courseMapper;

    @Override
    @Transactional
    public void create(EnrollmentDTO enrollmentDTO) {

        User student = userRepository.findByIdOrThrowException(enrollmentDTO.getStudentId());

        Course course = courseRepository.findByIdOrThrowException(enrollmentDTO.getCourseId());

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());

        enrollmentRepository.save(enrollment);

    }

    @Override
    @Transactional
    public void delete(Long id) {

        Enrollment enrollment = enrollmentRepository.getEnrollmentByIdOrThrowException(id);

        enrollmentRepository.delete(enrollment);

    }

    @Override
    public PageableDTO getMyCourses(Integer page) {

        Sort sort = Sort.by(AbsLongEntity.Fields.id).ascending();

        Pageable pageable = PageRequest.of(page, 10, sort);

        User student = securityUtils.getCurrentUser();

        Page<Enrollment> enrollmentPage = enrollmentRepository.findAllByStudentId(student.getId(), pageable);

        List<Course> courses = new ArrayList<>();

        List<Enrollment> enrollments = enrollmentPage.getContent();

        for (Enrollment enrollment : enrollments) {

            courses.add(enrollment.getCourse());

        }

        List<CourseDTO> courseDTOS = courseMapper.toDTO(courses);

        return new PageableDTO(
                enrollmentPage.getSize(),
                enrollmentPage.getTotalElements(),
                enrollmentPage.getTotalPages(),
                !enrollmentPage.isLast(),
                !enrollmentPage.isFirst(),
                courseDTOS
        );

    }
}
