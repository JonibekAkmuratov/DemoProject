package com.example.demoproject.mapper.main;

import com.example.demoproject.dto.main.product.ProductCreateDTO;
import com.example.demoproject.dto.main.product.ProductDTO;
import com.example.demoproject.dto.main.product.ProductUpdateDTO;
import com.example.demoproject.entity.main.Product;
import com.example.demoproject.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper extends BaseMapper<Product, ProductDTO, ProductCreateDTO, ProductUpdateDTO> {

}
