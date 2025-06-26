package uz.dev.edusphere.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import uz.dev.edusphere.dto.EnrollmentDTO;
import uz.dev.edusphere.entity.Enrollment;

import java.util.List;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 12:00
 **/

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnrollmentMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "course.id", target = "courseId")
    EnrollmentDTO toDTO(Enrollment enrollment);

    List<EnrollmentDTO> toDTO(List<Enrollment> enrollments);


}
