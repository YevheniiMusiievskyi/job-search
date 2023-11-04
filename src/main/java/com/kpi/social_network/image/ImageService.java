package com.kpi.social_network.image;

import com.kpi.social_network.files.FilesService;
import com.kpi.social_network.files.model.File;
import com.kpi.social_network.image.dto.ImageDto;
import com.kpi.social_network.image.model.Image;
import com.kpi.social_network.s3.FilesStorage;
import com.kpi.social_network.users.model.User;
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
