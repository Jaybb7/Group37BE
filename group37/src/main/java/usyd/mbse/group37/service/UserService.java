package usyd.mbse.group37.service;

import org.springframework.stereotype.Service;
import usyd.mbse.group37.model.UserModel;
import usyd.mbse.group37.repository.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkIfUserExists(String emailId) {
        return userRepository.findById(emailId).isPresent();
    }

    public UserModel createUser(String email, String name) {
        UserModel user = new UserModel();
        user.setUserName(name);
        user.setEmailAddress(email);
        return userRepository.save(user);
    }

}
