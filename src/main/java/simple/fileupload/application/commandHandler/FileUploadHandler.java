package simple.fileupload.application.commandHandler;

import jakarta.validation.constraints.NotNull;
import simple.fileupload.application.command.FileUploadCommand;
import simple.fileupload.application.dto.FileInfoDTO;
import simple.fileupload.exception.VirusFoundException;

import java.io.IOException;

public interface FileUploadHandler {
    FileInfoDTO uploadFile(@NotNull FileUploadCommand fileUploadCommand) throws VirusFoundException, IOException;
}
