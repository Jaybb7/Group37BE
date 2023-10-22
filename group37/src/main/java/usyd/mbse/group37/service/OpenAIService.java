package usyd.mbse.group37.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

@Slf4j
@Service
public class OpenAIService {

    public static String chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
        String model = "gpt-3.5-turbo";
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            return extractMessageFromJSONResponse(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content")+ 11;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }

    public String[] generateQuestionsForPurpose(String purpose) {
        String input = "Please generate 3 scenario based question in an array (level 3-5 complexity based on a complexity scale from 1-10) "
                +
                "that prompt user with a scenario  to assess the "+ purpose +" personality of the person. "
                +
                "The response will be purely textual. . No explanation is needed.";
        return chatGPT(input).split("\\\\n");
    }

    public Object generateDocumentSample(String information) {
        String input = "create a resume template for me inside the tag <TEMPLATE> with my information as " + information;
        return chatGPT(input).split("\\\\n");
    }

    public Object mockInterview(String question) {
        return chatGPT(question).split("\\\\n");
    }
}
