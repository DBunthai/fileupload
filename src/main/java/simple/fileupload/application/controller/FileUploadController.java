package simple.fileupload.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.EntityResponse;
import simple.fileupload.application.command.FileUploadCommand;
import simple.fileupload.application.commandHandler.FileUploadHandler;
import simple.fileupload.application.dto.ApiResponse;
import simple.fileupload.application.dto.FileInfoDTO;
import simple.fileupload.exception.VirusFoundException;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadHandler fileUploadHandler;

    @PostMapping("/upload")
    public ApiResponse<FileInfoDTO> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, VirusFoundException {

        FileUploadCommand fileUploadCommand = FileUploadCommand.builder()
            .fileName(file.getOriginalFilename())
            .fileSize(file.getSize())
            .fileType(file.getContentType())
            .fileContent(file.getInputStream())
            .build();

        return ApiResponse.success(fileUploadHandler.uploadFile(fileUploadCommand));
    }

    @GetMapping("/download/{filekey}")
    public ResponseEntity<String> download(@PathVariable("filekey") String filekey) {
        return ResponseEntity.ok("Loading...");
    }


}
