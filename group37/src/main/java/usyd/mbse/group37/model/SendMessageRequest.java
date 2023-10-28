package usyd.mbse.group37.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendMessageRequest {

    private String from;
    private String to;
    private String message;
}

