package com.kpi.social_network.image;

import com.kpi.social_network.image.dto.ImageDto;
import com.kpi.social_network.image.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDto imageToImageDto(Image image);
}
