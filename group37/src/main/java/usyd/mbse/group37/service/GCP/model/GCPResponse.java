package usyd.mbse.group37.service.GCP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GCPResponse {

    private Sentiment documentSentiment;
    private String languageCode;
    private List<Sentence> sentences;
    private boolean languageSupported;



}
