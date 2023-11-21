package xyz.weizj.manger.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.weizj.manger.service.FileUploadService;
import xyz.weizj.model.vo.common.Result;
import xyz.weizj.model.vo.common.ResultCodeEnum;

@Tag(name = "文件上传")
@RestController
@RequestMapping("/admin/system")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping(value = "/fileUpload")
    public Result<String> fileUploadervice(@RequestParam(value = "file")MultipartFile multipartFile){
        return Result.build(fileUploadService.fileUpload(multipartFile), ResultCodeEnum.SUCCESS);
    }
}
