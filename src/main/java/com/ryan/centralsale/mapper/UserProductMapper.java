package com.ryan.centralsale.mapper;

import com.ryan.centralsale.model.Product;
import com.ryan.centralsale.model.User;
import com.ryan.centralsale.model.UserProduct;
import com.ryan.centralsale.model.dto.UserProductDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {
        ProductMapper.class
        }
)
public interface UserProductMapper {

    UserProductDTO toDTO(UserProduct userProduct);

    @Mapping(target = "trackerId", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "priceCheck", source = "product.currentPrice")
    @Mapping(target = "notifyMe", constant = "true")  // Default value
    @Mapping(target = "active", constant = "true")     // Default value
    UserProduct toEntity(User user, Product product);



}
