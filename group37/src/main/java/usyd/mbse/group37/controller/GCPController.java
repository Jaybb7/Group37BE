package usyd.mbse.group37.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.mbse.group37.service.GCP.GCPService;
import usyd.mbse.group37.service.GCP.model.GCPResponse;
import usyd.mbse.group37.service.PurposeService;

import java.util.Map;

@Controller
@RequestMapping("/gcp")
@CrossOrigin(origins = "http://localhost:4200")
public class GCPController {

    private final GCPService gcpService;

    private final PurposeService purposeService;

    public GCPController(GCPService gcpService, PurposeService purposeService) {
        this.gcpService = gcpService;
        this.purposeService = purposeService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/askGCP")
    public ResponseEntity<?> contactGCP(@RequestParam String purpose, @RequestParam String answer1,@RequestParam String answer2,@RequestParam String answer3, @RequestParam String userId){
        try {
            GCPResponse a = gcpService.requestGCP(answer1);
            GCPResponse b = gcpService.requestGCP(answer2);
            GCPResponse c = gcpService.requestGCP(answer3);
            purposeService.createAPurpose(purpose,userId, a.getDocumentSentiment().getScore()+b.getDocumentSentiment().getScore()+c.getDocumentSentiment().getScore());
            return new ResponseEntity<>(Map.of("data", "Answers Processed Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("data", "Something Went Wrong, Please Try Again in few Minutes"), HttpStatus.OK);
        }
    }
}
