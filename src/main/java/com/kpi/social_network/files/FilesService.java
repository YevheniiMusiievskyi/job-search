package com.kpi.social_network.files;

import com.kpi.social_network.files.model.File;
import com.kpi.social_network.s3.FilesStorage;
import com.kpi.social_network.users.model.User;
import com.kpi.social_network.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilesService {

	private final FilesRepository filesRepository;
	private final FilesStorage filesStorage;

	private File save(File file) {
		return filesRepository.save(file);
	}

	public File uploadAndSave(MultipartFile file, String path) {
		String key = upload(file, path);
		var uploadedFile = new File()
				.setPath(key)
				.setContentType(file.getContentType());

		return save(uploadedFile);
	}

	public String upload(MultipartFile file, String path) {
		String extension = FileUtils.getExtension(file.getOriginalFilename());

		if (StringUtils.isBlank(path)) {
			path = "/";
		} else if (!path.endsWith("/")) {
			path = path + "/";
		}

		String key = path + UUID.randomUUID() + extension;
		filesStorage.upload(key, file);
		return key;
	}
}
