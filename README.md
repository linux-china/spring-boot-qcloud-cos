Spring Boot starter for qcloud COS
==============================================
Spring Boot for [腾讯云对象存储服务](http://wiki.qcloud.com/wiki/COS%E4%BA%A7%E5%93%81%E4%BB%8B%E7%BB%8D)

### 如何使用

* 在pom.xml添加以下依赖: 
      
        <dependency>
            <groupId>com.qcloud</groupId>
            <artifactId>cos-sdk</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>

* 在application.properties添加以下配置:

        spring.cos.app-id=111
        spring.cos.secret-id=xxx
        spring.cos.secret-key=yyy
        spring.cos.bucket-name=bucketName
* 接下来就是在你的代码中引用对应的服务啦:
        
        @Autowired
        private FileStorageService fileStorageService;

### 提供的服务列表

* com.qcloud.cos.api.CosCloud : COS Cloud操作
* com.qcloud.cos.api.BucketOperation : bucket操作
* org.mvnsearch.boot.cos.FileStorageService : 通用文件存储操作

### 参考

* COS产品介绍:  http://www.qcloud.com/wiki/COS%E4%BA%A7%E5%93%81%E4%BB%8B%E7%BB%8D
* COS Java SDK: https://github.com/linux-china/qcloud-cos-java-sdk