package usyd.mbse.group37.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import usyd.mbse.group37.exception.UserNotFoundException;
import usyd.mbse.group37.model.UserProfileModel;
import usyd.mbse.group37.repository.UserProfileRepository;
import usyd.mbse.group37.service.UserprofileService;

import java.io.IOException;

@Controller
@RequestMapping("/userProfile")
@CrossOrigin(origins = "http://localhost:4200")
public class UserProfileController {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserprofileService userprofileService;

    @PostMapping("/uploadProfilePhoto")
    public String uploadProfilePhoto(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) throws IOException {
        try {
            userprofileService.setProfilePhoto(userId, file.getBytes());
            return "redirect:/profile";
        } catch (UserNotFoundException e) {
            return "errorPage";
        }
    }

    @GetMapping("/{userId}/userprofile")
    public UserProfileModel getUserProfile(@PathVariable Long userId) {
        return userprofileService.getUserProfile(userId);
    }

}
