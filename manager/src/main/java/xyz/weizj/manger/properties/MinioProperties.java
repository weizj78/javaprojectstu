package xyz.weizj.manger.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "stu.minio")
public class MinioProperties {
    private String endPointUrl;
    private String accessKey;
    private String secreKey;
    private String bucketName;
}
