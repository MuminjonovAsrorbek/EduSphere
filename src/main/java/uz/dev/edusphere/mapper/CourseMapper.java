package uz.dev.edusphere.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import uz.dev.edusphere.dto.CourseDTO;
import uz.dev.edusphere.entity.Course;

import java.util.List;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 10:45
 **/

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {LongMapper.class, LessonMapper.class, AssignmentMapper.class})
public interface CourseMapper {

    @Mapping(source = "teacher.id", target = "teacherId")
    CourseDTO toDTO(Course course);

    List<CourseDTO> toDTO(List<Course> courses);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "assignments", ignore = true)
    Course toEntity(CourseDTO courseDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "assignments", ignore = true)
    Course updateCourse(CourseDTO courseDTO, @MappingTarget Course course);
}
