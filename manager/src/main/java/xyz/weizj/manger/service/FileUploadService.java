package xyz.weizj.manger.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    public abstract String fileUpload(MultipartFile multipartFile);
}
