package usyd.mbse.group37.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import usyd.mbse.group37.exception.UserNotFoundException;
import usyd.mbse.group37.model.UserProfileModel;
import usyd.mbse.group37.repository.UserProfileRepository;
import usyd.mbse.group37.service.UserprofileService;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("userprofile")
@CrossOrigin(origins = "http://localhost:4200")
public class UserProfileController {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserprofileService userprofileService;

    @PostMapping("/uploadProfilePhoto/{userId}")
    public String uploadProfilePhoto(@RequestParam("file") MultipartFile file, @PathVariable("userId") Long userId) throws IOException {
        try {
            userprofileService.setProfilePhoto(userId, file.getBytes());
            return "redirect:/userprofile";
        } catch (UserNotFoundException e) {
            return "errorPage";
        }
    }

    @PostMapping("/uploadStatusPhoto/{userId}")
    public String uploadStatusPhoto(@RequestParam("file") MultipartFile file, @PathVariable("userId") Long userId) throws IOException {
        try {
            userprofileService.setStatusPhoto(userId, file.getBytes());
            return "redirect:/userprofile";
        } catch (UserNotFoundException e) {
            return "errorPage";
        }
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<?> userProfilePage(@PathVariable("userId") String userIdStr) {
        try {
            Long userId = Long.parseLong(userIdStr);
            UserProfileModel userProfile = userprofileService.getUserProfile(userId);
            if (userProfile != null) {
                return new ResponseEntity<>(Map.of("data", userProfile), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("error", "User profile not found"), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(Map.of("error", "Invalid user ID format"), HttpStatus.BAD_REQUEST);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(Map.of("error", "User not found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Something went wrong, please try again in a few minutes"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/updateUserProfile/{userId}")
    public ResponseEntity<?> updateUserAndUploadProfilePhoto(
            @PathVariable Long userId,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String linkedInLink,
            @RequestParam(required = false) String bio,
            @RequestParam(required = false) String twitterLink,
            @RequestParam(required = false) String instagramLink,
            @RequestParam(required = false) String birthDate,
            @RequestParam(required = false) String currentPurpose

    ) {
        try {
            UserProfileModel userProfileToUpdate = new UserProfileModel();
            // Set the fields if they are provided
            if (address != null && !address.isEmpty()) userProfileToUpdate.setAddress(address);
            if (linkedInLink != null && !linkedInLink.isEmpty()) userProfileToUpdate.setLinkedInLink(linkedInLink);
            if (bio != null && !bio.isEmpty()) userProfileToUpdate.setBio(bio);
            if (twitterLink != null && !twitterLink.isEmpty()) userProfileToUpdate.setTwitterLink(twitterLink);
            if (instagramLink != null && !instagramLink.isEmpty()) userProfileToUpdate.setInstagramLink(instagramLink);
            if (birthDate != null && !birthDate.isEmpty()) userProfileToUpdate.setBirthDate(birthDate);  // Consider parsing if not a String
            if (currentPurpose != null && !currentPurpose.isEmpty()) userProfileToUpdate.setCurrentPurpose(currentPurpose);
            // Call the service method to update the user profile
            userprofileService.updateUserProfile(userId, userProfileToUpdate);

            return new ResponseEntity<>(Map.of("message", "User profile updated successfully."), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(Map.of("error", "User not found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Something went wrong, please try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
