package usyd.mbse.group37.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.mbse.group37.service.OpenAIService;

import java.util.Map;

@Controller
@RequestMapping("/ai")
@CrossOrigin(origins = "http://localhost:4200")
public class OpenAIController {

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/generateQuestions")
    public ResponseEntity<?> generateQuestions(@RequestParam String purpose){
        return new ResponseEntity<>(Map.of("data", openAIService.generateQuestionsForPurpose(purpose)), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/generateDocument")
    public ResponseEntity<?> generateDocumentSample(@RequestParam String information){
        return new ResponseEntity<>(Map.of("data", openAIService.generateDocumentSample(information)), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/mockInterview")
    public ResponseEntity<?> mockInterview(@RequestParam String question){
        try{
            Object answer = openAIService.mockInterview(question);
            return new ResponseEntity<>(Map.of("data", answer), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/chat")
    public ResponseEntity<?> askLLM(@RequestParam String question){
        try{
            Object answer = openAIService.mockInterview(question);
            return new ResponseEntity<>(Map.of("data", answer), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

}
