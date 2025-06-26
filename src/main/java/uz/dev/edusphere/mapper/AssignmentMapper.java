package uz.dev.edusphere.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import uz.dev.edusphere.dto.AssignmentDTO;
import uz.dev.edusphere.entity.Assignment;

import java.util.List;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 12:58
 **/

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AssignmentMapper {

    @Mapping(target = "courseId", source = "course.id")
    AssignmentDTO toDTO(Assignment assignment);

    List<AssignmentDTO> toDTO(List<Assignment> assignments);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "course", ignore = true)
    Assignment toEntity(AssignmentDTO assignmentDTO);

}
