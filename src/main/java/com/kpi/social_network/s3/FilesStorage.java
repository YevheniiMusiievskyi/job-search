package com.kpi.social_network.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.kpi.social_network.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class FilesStorage {

	private final AmazonS3 amazonS3;
	@Value("${aws.s3.host-url}")
	private String hostUrl;
	@Value("${aws.s3.bucket}")
	private String bucket;

	@PostConstruct
	void init() {
		hostUrl = StringUtils.appendIfMissing(hostUrl, "/");
		bucket = StringUtils.appendIfMissing(bucket, "/");
	}

	public void upload(String key, MultipartFile file) {
		try {
			upload(key, file.getInputStream(), file.getSize(), file.getContentType());
		} catch (IOException e) {
			Utils.rethrow(e);
		}
	}

	public void upload(String key, InputStream input, long size, String contentType) {
		var metadata = new ObjectMetadata();
		metadata.setContentLength(size);
		metadata.setContentType(contentType);

		amazonS3.putObject(bucket, key, input, metadata);
	}

	public S3Object getFile(String key) {
		return amazonS3.getObject(bucket, key);
	}

	public String getAbsolutePath(String key) {
		return hostUrl + bucket + key;
	}
}
