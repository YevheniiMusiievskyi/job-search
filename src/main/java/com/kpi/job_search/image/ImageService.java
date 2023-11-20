package com.kpi.job_search.image;

import com.kpi.job_search.files.FilesService;
import com.kpi.job_search.files.model.File;
import com.kpi.job_search.image.dto.ImageDto;
import com.kpi.job_search.image.model.Image;
import com.kpi.job_search.s3.FilesStorage;
import com.kpi.job_search.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final FilesService filesService;
    private final User currentUser;
    private final ImageMapper imageMapper;
    private final FilesStorage filesStorage;

    public ImageDto uploadAndSave(MultipartFile file) {
        String path = "posts/" + currentUser.getId();
        File savedFile = filesService.uploadAndSave(file, path);
        Image image = imageRepository.save(fileToImage(savedFile));

        return imageMapper.imageToImageDto(image);
    }

    private Image fileToImage(File file) {
        return new Image()
                .setLink(filesStorage.getAbsolutePath(file.getPath()))
                .setFile(file);
    }
}
