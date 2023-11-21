package xyz.weizj;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class TestMinio {
    @Test
    public void testMinio() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://127.0.0.1:9001")
                .credentials("admin123456", "admin123456")
                .build();

//        rl	"http://localhost:9090/api/v1/service-account-credentials"
//        accessKey	"admin123456"
//        secretKey	"admin123456"
//        api	"s3v4"
//        path	"auto"
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("xyz-bucket").build());
        if (!found){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("xyz-bucket").build());
        }else {
            System.out.println("Bucket xyz-bucket already Exists.");
        }




        System.out.println("Hello World");
    }
}
