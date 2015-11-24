package org.mvnsearch.boot.cos;

import com.qcloud.cos.api.BucketOperation;
import com.qcloud.cos.api.CosCloud;
import org.mvnsearch.boot.cos.impl.FileStorageServiceOssImpl;
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
    @Autowired
    private CosProperties properties;

    @Bean
    public CosCloud cosCloud() {
        return new CosCloud(properties.getAppId(), properties.getSecretId(), properties.getSecretKey());
    }

    @Bean
    public BucketOperation bucketOperation(CosCloud cosCloud) {
        return cosCloud.getBucketOperation(properties.getBucketName());
    }

    @Bean
    public FileStorageService cosFileStorageService(CosCloud cosCloud) {
        return new FileStorageServiceOssImpl(cosCloud, properties);
    }
}
