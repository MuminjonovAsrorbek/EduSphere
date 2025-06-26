package uz.dev.edusphere.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import uz.dev.edusphere.dto.LessonDTO;
import uz.dev.edusphere.entity.Lesson;

import java.util.List;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 12:21
 **/

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LessonMapper {

    @Mapping(source = "course.id", target = "courseId")
    LessonDTO toDTO(Lesson lesson);

    List<LessonDTO> toDTO(List<Lesson> lessons);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "course", ignore = true)
    Lesson toEntity(LessonDTO lessonDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "course", ignore = true)
    Lesson updateLesson(LessonDTO lessonDTO, @MappingTarget Lesson lesson);
}
