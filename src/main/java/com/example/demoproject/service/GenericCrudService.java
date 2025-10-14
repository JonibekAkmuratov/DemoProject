package com.example.demoproject.service;

import com.example.demoproject.criteria.GenericCriteria;
import com.example.demoproject.dto.*;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;



public interface GenericCrudService<
        D extends DTO,
        CD extends DTO,
        UD extends DTO,
        C extends GenericCriteria,
        ID extends Serializable> extends GenericService {
    ResponseEntity<Data<List<D>>> getAll(@NonNull C criteria);

    ResponseEntity<Data<D>> get(@NonNull ID id);

    ResponseEntity<Data<ID>> create(@NonNull CD DTO);

    ResponseEntity<Data<Boolean>> delete(@NonNull ID id);

    ResponseEntity<Data<Boolean>> update(@NonNull UD DTO);
}
