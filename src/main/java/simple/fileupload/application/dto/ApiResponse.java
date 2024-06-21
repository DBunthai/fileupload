package simple.fileupload.application.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiResponse<T> {

    String message;
    String status;
    T data;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .status("0")
                .message("Success")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .status("1")
                .message(message)
                .data(null)
                .build();
    }
}
