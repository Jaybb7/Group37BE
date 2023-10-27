package usyd.mbse.group37.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usyd.mbse.group37.service.UserScoreService;

@RestController
@RequestMapping("/api/user-score")
public class UserScoreController {

    @Autowired
    private UserScoreService userScoreService;

    //
    @GetMapping("/generate-questions/{userId}")
    public ResponseEntity<String[]> generateQuestions(@PathVariable Long userId) {
        try {
            String[] questions = userScoreService.generatePurposeBasedQuestionFromAI(userId);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //
    @PostMapping("/calculate-score")
    public ResponseEntity<Void> calculateScore(@RequestBody String userResponse) {
        try {
            userScoreService.calculateScoreUsingGCP(userResponse);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
