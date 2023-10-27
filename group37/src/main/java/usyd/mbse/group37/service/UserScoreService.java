package usyd.mbse.group37.service;

import com.google.cloud.language.v1.LanguageServiceClient;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import usyd.mbse.group37.exception.NoPurposeFoundException;
import usyd.mbse.group37.exception.UserNotFoundException;
import usyd.mbse.group37.exception.UserScoreNotFoundException;
import usyd.mbse.group37.model.PurposeModel;
import usyd.mbse.group37.model.UserModel;
import usyd.mbse.group37.model.UserScoreModel;
import usyd.mbse.group37.repository.PurposeRepository;
import usyd.mbse.group37.repository.UserScoreRepository;
import usyd.mbse.group37.service.GCP.GCPService;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserScoreService {
    private final UserScoreRepository userScoreRepository;
    private final PurposeRepository purposeRepository;
    private final OpenAIService openAIService;
    private final GCPService gcpService;

    public UserScoreService(UserScoreRepository userScoreRepository, PurposeRepository purposeRepository, OpenAIService openAIService, GCPService gcpService) {
        this.userScoreRepository = userScoreRepository;
        this.purposeRepository = purposeRepository;
        this.openAIService = openAIService;
        this.gcpService = gcpService;
    }
    public void calculateScoreUsingGCP(String userResponse) {
        try {
            float sentimentScore = gcpService.analyzeSentiment(userResponse);
            // Further process the score (if necessary)
            // can consider combining sentiment scores with other factors, such as the user's work experience, educational background, etc.
            float profileScore = processScore(sentimentScore);
            UserScoreModel userScore = new UserScoreModel();
            userScore.setScore(Float.toString(profileScore));
            userScoreRepository.save(userScore);
        } catch (Exception e) {
            log.error("Error during sentiment analysis.", e);
        }
    }

    private float processScore(float sentimentScore) {
        //may need further process
        return sentimentScore;
    }

    public String[] generatePurposeBasedQuestionFromAI(Long userId) {
        try {
            List<PurposeModel> purposes = purposeRepository.findAllByUserId(userId);
            if (purposes.isEmpty()) {
                throw new NoPurposeFoundException("No purpose found for user with ID: " + userId);
            }
            String allPurposes = purposes.stream()
                    .map(PurposeModel::getPurpose)
                    .collect(Collectors.joining(", "));

            return openAIService.generateQuestionsForPurpose(allPurposes);
        } catch (DataAccessException e) {
            log.error("Error accessing the database", e);
            throw new RuntimeException("Database error occurred.", e);
        }
    }

}
