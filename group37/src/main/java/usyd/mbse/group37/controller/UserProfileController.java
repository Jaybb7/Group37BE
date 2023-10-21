package usyd.mbse.group37.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
            return "redirect:../profile";
        } catch (UserNotFoundException e) {
            return "errorPage";
        }
    }

    @PostMapping("/uploadStatusPhoto/{userId}")
    public String uploadStatusPhoto(@RequestParam("file") MultipartFile file, @PathVariable("userId") Long userId) throws IOException {
        try {
            userprofileService.setStatusPhoto(userId, file.getBytes());
            return "redirect:../userprofile";
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
            //model.addAttribute("userProfile", userProfile);
            return new ResponseEntity<>(Map.of("data", "Something Went Wrong, Please Try Again in few Minutes"), HttpStatus.OK);

        } catch (NumberFormatException e) {
            return new ResponseEntity<>(Map.of("data", "Something Went Wrong, Please Try Again in few Minutes"), HttpStatus.OK);

        }
    }

}
