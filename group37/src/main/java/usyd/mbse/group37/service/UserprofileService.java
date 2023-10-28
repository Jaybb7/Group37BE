package usyd.mbse.group37.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import usyd.mbse.group37.exception.UserNotFoundException;
import usyd.mbse.group37.model.UserModel;
import usyd.mbse.group37.model.UserProfileModel;
import usyd.mbse.group37.repository.UserProfileRepository;
import usyd.mbse.group37.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
public class UserprofileService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;


    public UserprofileService(UserProfileRepository userRepository, UserRepository userRepository1) {
        this.userProfileRepository = userRepository;
        this.userRepository = userRepository1;
    }


    public void setStatusPhoto(Long userId, byte[] statusPhoto) {
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            userProfileModel.setStatusPhoto(statusPhoto);
            userProfileRepository.save(userProfileModel);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public void setProfilePhoto(Long userId, byte[] profilePhoto) {
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            userProfileModel.setProfilePhoto(profilePhoto);
            userProfileRepository.save(userProfileModel);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public UserProfileModel getUserProfile(Long userId) {
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            return userProfileOptional.get();
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public void updateUserProfile(Long userId, UserProfileModel userProfileModel){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            UserProfileModel existingUserProfile = userProfileOptional.get();

            if (userProfileModel.getTwitterLink() != null) {
                existingUserProfile.setTwitterLink(userProfileModel.getTwitterLink());
            }
            if (userProfileModel.getInstagramLink() != null) {
                existingUserProfile.setInstagramLink(userProfileModel.getInstagramLink());
            }
            if (userProfileModel.getLinkedInLink() != null) {
                existingUserProfile.setLinkedInLink(userProfileModel.getLinkedInLink());
            }
            if (userProfileModel.getCurrentPurpose() != null) {
                existingUserProfile.setCurrentPurpose(userProfileModel.getCurrentPurpose());
            }
            if (userProfileModel.getAddress() != null) {
                existingUserProfile.setAddress(userProfileModel.getAddress());
            }
            if (userProfileModel.getLocation() != null) {
                existingUserProfile.setLocation(userProfileModel.getLocation());
            }
            if (userProfileModel.getBirthDate() != null) {
                existingUserProfile.setBirthDate(userProfileModel.getBirthDate());
            }
            if (userProfileModel.getBio() != null) {
                existingUserProfile.setBio(userProfileModel.getBio());
            }

            userProfileRepository.save(existingUserProfile);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
