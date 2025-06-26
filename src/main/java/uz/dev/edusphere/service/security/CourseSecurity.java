package uz.dev.edusphere.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dev.edusphere.entity.Assignment;
import uz.dev.edusphere.entity.Course;
import uz.dev.edusphere.entity.Enrollment;
import uz.dev.edusphere.entity.User;
import uz.dev.edusphere.enums.Role;
import uz.dev.edusphere.repository.*;
import uz.dev.edusphere.utils.SecurityUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 12:47
 **/

@Service(value = "courseSecurity")
@RequiredArgsConstructor
public class CourseSecurity {

    private final CourseRepository courseRepository;

    private final SecurityUtils securityUtils;

    private final AssignmentRepository assignmentRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final UserRepository userRepository;

    private final ParentStudentLinkRepository parentStudentLinkRepository;

    public boolean canAddAssignee(Long courseId) {

        Course course = courseRepository.findById(courseId).orElse(null);

        if (Objects.isNull(course)) return false;

        User currentUser = securityUtils.getCurrentUser();

        if (currentUser.getRole().equals(Role.ADMIN)) return true;

        return course.getTeacher().getId().equals(currentUser.getId());
    }

    public boolean canViewSubmission(Long assignmentId) {

        Assignment assignment = assignmentRepository.findById(assignmentId).orElse(null);

        if (Objects.isNull(assignment)) return false;

        User currentUser = securityUtils.getCurrentUser();

        if (currentUser.getRole().equals(Role.ADMIN)) return true;

        return assignment.getCourse().getTeacher().getId().equals(currentUser.getId());

    }

    public boolean canAssignmentSubmit(Long assignmentId) {

        Assignment assignment = assignmentRepository.findById(assignmentId).orElse(null);

        if (Objects.isNull(assignment)) return false;

        User currentUser = securityUtils.getCurrentUser();

        List<Enrollment> enrollments = enrollmentRepository.findByStudent(currentUser);

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getCourse().equals(assignment.getCourse())) {

                return true;

            }

        }

        return currentUser.getRole().equals(Role.ADMIN);

    }

    public boolean canViewMyChildrenGrades(Long childrenId) {

        User parent = securityUtils.getCurrentUser();

        Optional<User> optionalUser = userRepository.findById(childrenId);

        if (optionalUser.isEmpty()) return false;

        if (parent.getRole().equals(Role.ADMIN)) return true;

        User children = optionalUser.get();

        return parentStudentLinkRepository.existsByParentAndStudent(parent, children);
    }

}
