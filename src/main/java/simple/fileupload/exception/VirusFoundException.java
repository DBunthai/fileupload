package simple.fileupload.exception;

public class VirusFoundException extends FileUploadException {

    private static final String message = "Virus found in the file";
    private static final String status = "BAD_REQUEST";
    private static final String code = "01";

    public VirusFoundException() {
        super(message, status, code);
    }
}
