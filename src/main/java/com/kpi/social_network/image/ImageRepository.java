package com.kpi.social_network.image;

import com.kpi.social_network.image.model.Image;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface ImageRepository  extends CrudRepository<Image, UUID> {
}