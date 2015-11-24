package org.mvnsearch.boot.cos;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * COS properties
 *
 * @author linux_china
 */
@ConfigurationProperties(
        prefix = "spring.cos"
)
public class CosProperties {
    /**
     * app id
     */
    private int appId;
    /**
     * secretID
     */
    private String secretId;
    /**
     * secret key
     */
    private String secretKey;
    /**
     * bucket name
     */
    private String bucketName;

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
