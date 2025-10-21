package com.example.demoproject.mapper.het;

import com.example.demoproject.dto.het.TransformerRegionsDTO;
import com.example.demoproject.entity.het.Transformer;
import com.example.demoproject.mapper.GenericMapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransformerMapper extends GenericMapper {



    @Mapping(target = "regionId", source = "region.id")
    @Mapping(target = "regionName", source = "region.name")
    @Mapping(target = "districtId", source = "district.id")
    @Mapping(target = "districtName", source = "district.name")
    TransformerRegionsDTO toRegionDTO(Transformer transformer);

    List<TransformerRegionsDTO> toRegionDTO(List<Transformer> transformers);

}
