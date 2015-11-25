package org.mvnsearch.boot.cos;

import com.qcloud.cos.api.BucketOperation;
import com.qcloud.cos.api.CosClient;
import org.mvnsearch.boot.cos.impl.FileStorageServiceCosImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * COS auto configuration
 *
 * @author linux_china
 */
@Configuration
@EnableConfigurationProperties(CosProperties.class)
public class CosAutoConfiguration {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private CosProperties properties;

    @Bean
    public CosClient cosClient() {
        return new CosClient(properties.getAppId(), properties.getSecretId(), properties.getSecretKey());
    }

    @Bean
    public BucketOperation bucketOperation(CosClient cosClient) {
        return cosClient.getBucketOperation(properties.getBucketName());
    }

    @Bean
    public FileStorageService cosFileStorageService(CosClient cosClient) {
        return new FileStorageServiceCosImpl(cosClient, properties);
    }
}
