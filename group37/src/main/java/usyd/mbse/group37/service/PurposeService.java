package usyd.mbse.group37.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import usyd.mbse.group37.model.PurposeModel;
import usyd.mbse.group37.model.UserModel;
import usyd.mbse.group37.repository.PurposeRepository;
import usyd.mbse.group37.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
public class PurposeService {

    private final PurposeRepository purposeRepository;

    private final UserRepository userRepository;

    public PurposeService(PurposeRepository purposeRepository, UserRepository userRepository) {
        this.purposeRepository = purposeRepository;
        this.userRepository = userRepository;
    }

    public void createAPurpose( String purpose, String userId, double purposeScore) {
        UserModel user =  userRepository.findByUserId(userId);
        PurposeModel purposeModel = new PurposeModel();
        purposeModel.setPurpose(purpose);
        purposeModel.setPurposeScore(0);
        purposeModel.setUserModel(user);
        purposeRepository.save(purposeModel);
//        return Optional.of(purposeRepository.save(purposeModel));
    }

    public Optional<PurposeModel> findPurposeRecords(String purpose, Long userId) {
        return purposeRepository.findByPurposeAndUserID(purpose, userId);
    }

}
