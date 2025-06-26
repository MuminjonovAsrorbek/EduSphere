package uz.dev.edusphere.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import uz.dev.edusphere.dto.GradeDTO;
import uz.dev.edusphere.entity.Grade;

import java.util.List;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 13:33
 **/
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GradeMapper {

    @Mapping(target = "submissionId", source = "submission.id")
    GradeDTO toDTO(Grade grade);

    List<GradeDTO> toDTO(List<Grade> grades);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "submission", ignore = true)
    @Mapping(target = "gradedAt", ignore = true)
    Grade toEntity(GradeDTO gradeDTO);
}
