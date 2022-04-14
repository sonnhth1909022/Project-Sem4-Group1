package com.projectsem4.backend.dto.cast;

import com.projectsem4.backend.entity.Cast;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CastMapper {
    CastMapper INSTANCE = Mappers.getMapper(CastMapper.class);

    //CastDto mappers
    Cast castDtoToCast(CastDto castDto);
    CastDto castToCastDto(Cast cast);

    //CastDtoRes mappers
    Cast castDtoResToCast(CastDtoRes castDtoRes);
    CastDtoRes castToCastDtoRes(Cast cast);
    List<CastDtoRes> lsCastToCastDtoRes(List<Cast> casts);
}
