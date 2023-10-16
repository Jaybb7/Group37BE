package usyd.mbse.group37.service.GCP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TextSpan {
    private String content;
    private int beginOffset;
}
