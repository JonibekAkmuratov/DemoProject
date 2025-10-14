package com.example.demoproject.mapper;

import com.example.demoproject.dto.DTO;
import com.example.demoproject.entity.BaseDomain;
import lombok.NonNull;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;



/**
 * @param <E>  : Domain Class (Entity)
 * @param <D>  : Data Transfer Object (DTO)
 * @param <CD> : DTO for create
 * @param <UD> : DTO for update
 * @description : Mapper for basic operations
 */
public interface BaseMapper<
        E extends BaseDomain,
        D extends DTO,
        CD extends DTO,
        UD extends DTO> extends GenericMapper {
    D toDto(@NonNull E domain);

    List<D> toDto(@NonNull List<E> domains);

    E fromCreateDto(@NonNull CD dto);

    E fromUpdateDto(@NonNull UD dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    E partialUpdate(UD dto, @MappingTarget E domain);

}
