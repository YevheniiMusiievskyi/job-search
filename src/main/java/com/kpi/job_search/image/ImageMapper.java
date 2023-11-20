package com.kpi.job_search.image;

import com.kpi.job_search.image.dto.ImageDto;
import com.kpi.job_search.image.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDto imageToImageDto(Image image);
}
