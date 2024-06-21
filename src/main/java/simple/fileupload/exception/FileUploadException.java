package simple.fileupload.exception;

import lombok.Getter;
import lombok.Value;

@Getter
public abstract class FileUploadException extends Exception {

    protected final String message;
    protected final String status;
    protected final String code;

    public FileUploadException(String message, String status, String code) {
        super(message);
        this.message = message;
        this.status = status;
        this.code = code;
    }

}
