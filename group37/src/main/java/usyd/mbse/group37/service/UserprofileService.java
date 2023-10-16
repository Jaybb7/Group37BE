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

    public String getTwitterLink(Long userId) {

        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);

        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            return userProfileModel.getTwitterLink();
        } else {
            return "Twitter link not found";
        }
    }

    public void setTwitterLink(String twitterLink, Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            userProfileModel.setTwitterLink(twitterLink);
            userProfileRepository.save(userProfileModel);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public String getInstagramLink(Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);

        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            return userProfileModel.getInstagramLink();
        } else {
            return "Instagram link not found";
        }
    }

    public void setInstagramLink(String instagramLink, Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            userProfileModel.setInstagramLink(instagramLink);
            userProfileRepository.save(userProfileModel);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public String getLinkedInLink(Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);

        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            return userProfileModel.getLinkedInLink();
        } else {
            return "LinkedIn link not found";
        }
    }

    public void setLinkedInLink(String linkedInLinkLink, Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            userProfileModel.setLinkedInLink(linkedInLinkLink);
            userProfileRepository.save(userProfileModel);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public String getCurrentPurpose(Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);

        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            return userProfileModel.getCurrentPurpose();
        } else {
            return "Current purpose not found";
        }
    }

    public void setCurrentPurpose(String currentPurpose, Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            userProfileModel.setCurrentPurpose(currentPurpose);
            userProfileRepository.save(userProfileModel);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public String getAddress(Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);

        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            return userProfileModel.getAddress();
        } else {
            return "Address not found";
        }
    }

    public void setAddress(String address, Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            userProfileModel.setAddress(address);
            userProfileRepository.save(userProfileModel);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public long getUserProfileEntityId(Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);

        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            return userProfileModel.getUserProfileId();
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
    public String getLocation(Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);

        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            return userProfileModel.getLocation();
        } else {
            return "Location not found";
        }
    }

    public void setLocation(String location, Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            userProfileModel.setLocation(location);
            userProfileRepository.save(userProfileModel);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public String getBrithDate(Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);

        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            return userProfileModel.getBirthDate();
        } else {
            return "BirthDate not found";
        }
    }

    public void setBirthDate(String birthDate, Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            userProfileModel.setBirthDate(birthDate);
            userProfileRepository.save(userProfileModel);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public String getBio(Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);

        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            return userProfileModel.getBio();
        } else {
            return "Bio not found";
        }
    }

    public void setBio(String bio, Long userId){
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            userProfileModel.setBio(bio);
            userProfileRepository.save(userProfileModel);
        } else {
            throw new UserNotFoundException("User not found");
        }
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

    public byte[] getStatusPhoto(Long userId) {
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            return userProfileModel.getStatusPhoto();
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public byte[] getProfilePhoto(Long userId) {
        Optional<UserProfileModel> userProfileOptional = userProfileRepository.findByUserProfileId(userId);
        if (userProfileOptional.isPresent()) {
            UserProfileModel userProfileModel = userProfileOptional.get();
            return userProfileModel.getProfilePhoto();
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
