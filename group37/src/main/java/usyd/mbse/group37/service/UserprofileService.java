package usyd.mbse.group37.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import usyd.mbse.group37.exception.UserNotFoundException;
import usyd.mbse.group37.model.UserProfileModel;
import usyd.mbse.group37.repository.UserProfileRepository;

import java.util.Optional;

@Slf4j
@Service
public class UserprofileService {
    private final UserProfileRepository userProfileRepository;


    public UserprofileService(UserProfileRepository userRepository) {
        this.userProfileRepository = userRepository;
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
            existingUserProfile.setTwitterLink(userProfileModel.getTwitterLink());
            existingUserProfile.setInstagramLink(userProfileModel.getInstagramLink());
            existingUserProfile.setLinkedInLink(userProfileModel.getLinkedInLink());
            existingUserProfile.setCurrentPurpose(userProfileModel.getCurrentPurpose());
            existingUserProfile.setAddress(userProfileModel.getAddress());
            existingUserProfile.setLocation(userProfileModel.getLocation());
            existingUserProfile.setBirthDate(userProfileModel.getBirthDate());
            existingUserProfile.setBio(userProfileModel.getBio());
            existingUserProfile.setStatusPhoto(userProfileModel.getStatusPhoto());
            existingUserProfile.setProfilePhoto(userProfileModel.getProfilePhoto());
            userProfileRepository.save(existingUserProfile);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
