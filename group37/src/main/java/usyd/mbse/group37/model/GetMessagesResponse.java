package usyd.mbse.group37.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetMessagesResponse {

    private Long messageId;
    private Long userId;
    private String message;
    private String timeStamp;
}
