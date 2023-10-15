package usyd.mbse.group37.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendMessageRequest {

    private Long fromUser;
    private Long toUser;
    private String messageContent;
}

