package com.ryan.centralsale.mapper;

import com.ryan.centralsale.model.Product;
import com.ryan.centralsale.model.dto.ProductCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDTO(Product product);

    @Mapping(target="productId", ignore = true)
    Product toEntity(ProductCreateDTO productCreateDTO);
}
