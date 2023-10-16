package usyd.mbse.group37.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import usyd.mbse.group37.exception.UserNotFoundException;
import usyd.mbse.group37.model.UserProfileModel;
import usyd.mbse.group37.repository.UserProfileRepository;
import usyd.mbse.group37.service.UserprofileService;

import java.io.IOException;

@Controller
@RequestMapping("/{userId}/userprofile")
@CrossOrigin(origins = "http://localhost:4200")
public class UserProfileController {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserprofileService userprofileService;

    @PostMapping("/uploadProfilePhoto")
    public String uploadProfilePhoto(@RequestParam("file") MultipartFile file, @PathVariable("userId") Long userId) throws IOException {
        try {
            userprofileService.setProfilePhoto(userId, file.getBytes());
            return "redirect:/profile";
        } catch (UserNotFoundException e) {
            return "errorPage";
        }
    }

    @PostMapping("/uploadStatusPhoto")
    public String uploadStatusPhoto(@RequestParam("file") MultipartFile file, @PathVariable("userId") Long userId) throws IOException {
        try {
            userprofileService.setStatusPhoto(userId, file.getBytes());
            return "redirect:/profile";
        } catch (UserNotFoundException e) {
            return "errorPage";
        }
    }
    @GetMapping
    public String userProfilePage(@PathVariable Long userId, Model model) {
        UserProfileModel userProfile = userprofileService.getUserProfile(userId);
        model.addAttribute("userProfile", userProfile);

        return "userProfilePage";
    }

    @PostMapping
    public String updateUserProfile(
            @PathVariable Long userId,
            @ModelAttribute UserProfileModel userProfileModel) {
        try {
            userprofileService.updateUserProfile(userId, userProfileModel);
            return "redirect:/{userId}/userprofile";
        } catch (UserNotFoundException e) {
            return "errorPage";
        }
    }

}
