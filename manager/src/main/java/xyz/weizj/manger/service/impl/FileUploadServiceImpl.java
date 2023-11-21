package xyz.weizj.manger.service.impl;

import cn.hutool.core.date.DateUtil;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.weizj.manger.properties.MinioProperties;
import xyz.weizj.manger.service.FileUploadService;
import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private MinioProperties minioProperties;


    @Override
    public String fileUpload(MultipartFile multipartFile) {
        MinioClient client = MinioClient.builder()
                .endpoint(minioProperties.getEndPointUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecreKey())
                .build();
        try {
            boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!found){
                client.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            }else{
                System.out.println("bucket aready exist");
            }

            String dateDir = DateUtil.format(new Date(),"yyyyMMDD");
            String uuid = UUID.randomUUID().toString().replace("-","");
            String fileName = dateDir+"/" + uuid+multipartFile.getOriginalFilename();
            System.out.println(fileName);
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .object(fileName)
                    .build();

            client.putObject(putObjectArgs);
            return minioProperties.getEndPointUrl()+"/" + minioProperties.getBucketName() + "/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
