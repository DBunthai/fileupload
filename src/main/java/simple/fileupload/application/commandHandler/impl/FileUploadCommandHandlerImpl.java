package simple.fileupload.application.commandHandler.impl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import simple.fileupload.application.command.FileUploadCommand;
import simple.fileupload.application.commandHandler.FileUploadHandler;
import simple.fileupload.application.controller.FileUploadController;
import simple.fileupload.application.dto.FileInfoDTO;
import simple.fileupload.domain.FileInfo;
import simple.fileupload.exception.VirusFoundException;
import simple.fileupload.infrastructure.repository.FileInfoRepository;
import simple.fileupload.infrastructure.service.ClamavScan;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadCommandHandlerImpl implements FileUploadHandler {

    private final String DIR = "src/main/resources/zfilestorage";


    private final FileInfoRepository fileInfoRepository;
    private final ClamavScan clamavScan;

    @Override
    public FileInfoDTO uploadFile(@NotNull FileUploadCommand fileUploadCommand) throws VirusFoundException, IOException {


        InputStream fileStream = new BufferedInputStream(fileUploadCommand.getFileContent());
        int readLimit = 10 * 1024 * 1024;
        fileStream.mark(readLimit);

        scanFile(fileStream);
        fileStream.reset();

        String fileName = fileUploadCommand.getFileName();
        long fileSize = fileUploadCommand.getFileSize();
        String fileType = fileUploadCommand.getFileType();

        FileInfo fileInfo = FileInfo.builder()
            .name(fileName)
            .size(fileSize)
            .type(fileType)
            .build();

        FileInfo newFileInfo = fileInfoRepository.save(fileInfo);
        UUID fileKey = newFileInfo.getId();
        saveFile(fileStream, fileKey);
        fileStream.close();

        return FileInfoDTO.builder()
            .name(fileName)
            .size(fileSize)
            .type(fileType)
            .downloadUri(getDownloadUri(fileKey))
            .build();
    }

    private void scanFile(InputStream file) throws VirusFoundException {
        clamavScan.scan(file);
    }

    private void saveFile(InputStream file, UUID key) throws IOException {

        final Path uploadPath = Paths.get(DIR);
        Path path = uploadPath.resolve(key.toString());
        Files.copy(file, Path.of(path.toUri()), StandardCopyOption.REPLACE_EXISTING);
    }

    private String getDownloadUri(UUID key) {
        String url =
            MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "download", key.toString())
                .build()
                .toString();

        return url;
    }
}
