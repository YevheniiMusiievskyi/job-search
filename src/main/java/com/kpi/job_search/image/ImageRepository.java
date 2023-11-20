package com.kpi.job_search.image;

import com.kpi.job_search.image.model.Image;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface ImageRepository  extends CrudRepository<Image, UUID> {
}