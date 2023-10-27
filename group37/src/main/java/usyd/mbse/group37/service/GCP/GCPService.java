package usyd.mbse.group37.service.GCP;

import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import usyd.mbse.group37.service.GCP.model.GCPResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Service
public class GCPService {

    private static final String API_KEY = "AIzaSyDKEU8OcpQxrU6e6SlC87P0PY1TP3zGdjY";
    private static final String API_ENDPOINT =
            "https://language.googleapis.com/v2/documents:analyzeSentiment" + "?key=" + API_KEY;
    private static final String ENCODING_TYPE = "UTF8";
    private static final String GCP_REQUEST_JSON_BODY = "{\"document\":" +
            "{\"type\":\"PLAIN_TEXT\",\"languageCode\":\"en\",\"content\":\"%s\"}," +
            "\"encodingType\":\"%s\"}";


    public Object requestGCP(String payload) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String requestBody = String.format(
                GCP_REQUEST_JSON_BODY, payload, ENCODING_TYPE);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<?> response = restTemplate.exchange(API_ENDPOINT, HttpMethod.POST, entity,
                GCPResponse.class);
        System.out.println(response.getStatusCode());
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Unable to request external api: GCP sentiment analysis");
        }
        return response.getBody();
    }
    public float analyzeSentiment(String text) throws Exception {
        try (LanguageServiceClient languageService = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();
            AnalyzeSentimentResponse response = languageService.analyzeSentiment(doc);

            return response.getDocumentSentiment().getScore();
        } catch (Exception e) {
            throw new Exception("Error during sentiment analysis.", e);
        }
    }
}
