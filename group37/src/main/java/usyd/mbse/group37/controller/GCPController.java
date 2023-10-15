package usyd.mbse.group37.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usyd.mbse.group37.service.GCP.GCPService;

import java.util.Map;

@Controller
@RequestMapping("/gcp")
@CrossOrigin(origins = "http://localhost:4200")
public class GCPController {

    private final GCPService gcpService;

    public GCPController(GCPService gcpService) {
        this.gcpService = gcpService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/askGCP")
    public ResponseEntity<?> contactGCP(@RequestParam String text){
        try {
            return new ResponseEntity<>(Map.of("data", gcpService.requestGCP(text)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("data", "Something Went Wrong"), HttpStatus.OK);
        }
    }
}
