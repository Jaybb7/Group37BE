package usyd.mbse.group37.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import usyd.mbse.group37.exception.UserNotFoundException;
import usyd.mbse.group37.model.UserFriendship;
import usyd.mbse.group37.model.UserProfileModel;
import usyd.mbse.group37.repository.UserFriendshipRepository;
import usyd.mbse.group37.repository.UserProfileRepository;
import usyd.mbse.group37.service.UserprofileService;

import java.io.IOException;
import java.net.URI;
import java.util.*;

@Controller
@RequestMapping("userprofile")
@CrossOrigin(origins = "http://localhost:4200")
public class UserProfileController {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserprofileService userprofileService;

    @Autowired
    private UserFriendshipRepository userFriendshipRepository;

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


    @GetMapping("/{userId}")
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

    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<UserProfileModel>> getUserFriends(@PathVariable Long userId) {
        List<UserProfileModel> friends = new ArrayList<>();
        UserProfileModel userProfile = userProfileRepository.findById(userprofileService.getUserProfileId(userId)).orElse(null);

        if (userProfile == null) {
            return ResponseEntity.notFound().build();
        }

        List<UserFriendship> friendships = userFriendshipRepository.findByUser(userProfile);

        for (UserFriendship friendship : friendships) {
            UserProfileModel friend = friendship.getFriend();
            if (friend != null) {
                friends.add(friend);
            }
        }
        return ResponseEntity.ok(friends);
    }
    @PostMapping("/{userId}/addFriend")
    public ResponseEntity<String> addUserFriendship(
            @PathVariable Long userId,
            @RequestParam Long friendUserId,
            @RequestParam String type) {


        Optional<UserProfileModel> userProfile = userProfileRepository.findByUserProfileId(userId);
        Optional<UserProfileModel> friendProfile = userProfileRepository.findByUserProfileId(friendUserId);


        if (userProfile == null || friendProfile == null) {
            return ResponseEntity.badRequest().build();
        }
        boolean friendshipExistsDirect = userFriendshipRepository.existsByUserAndFriend(userProfile.get(), friendProfile.get());
        boolean friendshipExistsReverse = userFriendshipRepository.existsByUserAndFriend(friendProfile.get(), userProfile.get());

        if (friendshipExistsDirect || friendshipExistsReverse) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Friendship already exists");
        }
        UserFriendship friendship = new UserFriendship();
        friendship.setUser(userProfile.get());
        friendship.setFriend(friendProfile.get());
        UserFriendship.FriendshipType friendshipType;
        try {
            friendshipType = UserFriendship.FriendshipType.valueOf(type);
            friendship.setFriendshipType(friendshipType);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid friendship type provided.");
        }

        userFriendshipRepository.save(friendship);

        UserFriendship reverseFriendship = new UserFriendship();
        reverseFriendship.setUser(friendProfile.get());
        reverseFriendship.setFriend(userProfile.get());
        reverseFriendship.setFriendshipType(friendshipType);

        userFriendshipRepository.save(reverseFriendship);

        List<URI> uris = Arrays.asList(
                URI.create("/" + friendship.getFriendshipId().toString()),
                URI.create("/" + reverseFriendship.getFriendshipId().toString())
        );

        return ResponseEntity.created(uris.get(0)).body(uris.toString());
    }
    @Transactional
    @DeleteMapping("/{userId}/removeFriend")
    public ResponseEntity<String> removeUserFriendship(
            @PathVariable Long userId,
            @RequestParam Long friendUserId) {

        Optional<UserProfileModel> userProfile = userProfileRepository.findByUserProfileId(userId);
        Optional<UserProfileModel> friendProfile = userProfileRepository.findByUserProfileId(friendUserId);

        // Check if both user profiles exist
        if (!userProfile.isPresent() || !friendProfile.isPresent()) {
            return ResponseEntity.badRequest().body("User or friend user does not exist.");
        }

        boolean friendshipExistsDirect = userFriendshipRepository.existsByUserAndFriend(userProfile.get(), friendProfile.get());
        boolean friendshipExistsReverse = userFriendshipRepository.existsByUserAndFriend(friendProfile.get(), userProfile.get());

        // If neither a direct nor a reverse friendship exists, return a NOT FOUND status
        if (!friendshipExistsDirect && !friendshipExistsReverse) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Friendship does not exist.");
        }

        // Delete the direct friendship
        if (friendshipExistsDirect) {
            userFriendshipRepository.deleteByUserAndFriend(userProfile.get(), friendProfile.get());
        }

        // Delete the reverse friendship
        if (friendshipExistsReverse) {
            userFriendshipRepository.deleteByUserAndFriend(friendProfile.get(), userProfile.get());
        }

        return ResponseEntity.ok("Friendship successfully removed.");
    }


}
