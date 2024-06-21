package simple.fileupload.application.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FileInfoDTO {
    String name;
    String downloadUri;
    String type;
    long size;
}
