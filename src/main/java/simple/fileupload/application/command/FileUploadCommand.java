package simple.fileupload.application.command;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Range;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Value
@Builder
public class FileUploadCommand {

    @Range(min = 1, max = 100)
    @NotNull
    String fileName;

    // 20MB
    @Max(20971520)
    @NotNull
    long fileSize;

    @NotNull
    String fileType;

    @NotNull
    InputStream fileContent;
}
