package com.kpi.social_network.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	@Value("${aws.s3.host-url}")
	private String endpoint;
	@Value("${aws.s3.access-key}")
	private String accessKey;
	@Value("${aws.s3.secret-key}")
	private String secretKey;

	@Bean
	public AmazonS3 amazonS3() {
		return AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, null))
				.enablePathStyleAccess()
				.build();
	}
}
