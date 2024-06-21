package simple.fileupload.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import simple.fileupload.application.dto.ApiResponse;

@ControllerAdvice
@Log4j2
public class ExceptionControllerAdvice {

    @ExceptionHandler({Exception.class})
    public ApiResponse<String> handleException(Exception ex) {
        log.error("Error: ", ex);
        if(ex instanceof FileUploadException) {
            return ApiResponse.error(ex.getMessage());
        }
        return ApiResponse.error("General Error");
    }

}
