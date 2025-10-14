package com.ryan.centralsale.mapper;

import com.ryan.centralsale.model.Product;
import com.ryan.centralsale.model.dto.ProductCreateDTO;
import com.ryan.centralsale.model.dto.ProductPatchDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDTO(Product product);

    @Mapping(target="productId", ignore = true)
    Product toEntity(ProductCreateDTO productCreateDTO);

    ProductPatchDTO toPatchDTO(ProductCreateDTO product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "asin", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastChecked", ignore = true)
    @Mapping(target = "percentChange", ignore = true)
    @Mapping(target = "priceDrop", ignore = true)
    void updateEntityFromDto(ProductPatchDTO dto, @MappingTarget Product product);
}
