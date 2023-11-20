package com.kpi.job_search.image;

import com.kpi.job_search.image.dto.ImageDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
@Api(tags = "images")
public class ImageController {
    @Autowired
    ImageService imageService;

    @PostMapping
    public ImageDto post(@RequestBody MultipartFile file) throws IOException {
        return imageService.uploadAndSave(file);
    }
}
