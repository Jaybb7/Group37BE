package usyd.mbse.group37.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import usyd.mbse.group37.model.PurposeModel;
import usyd.mbse.group37.model.UserModel;
import usyd.mbse.group37.service.PurposeService;
import usyd.mbse.group37.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    private final PurposeService purposeService;

    public UserController(UserService userService, PurposeService purposeService) {
        this.userService = userService;
        this.purposeService = purposeService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/storeUser")
    public UserModel checkUser_createUser(@RequestParam String name, @RequestParam String email){
        if(!userService.checkIfUserExists(email)){
            return userService.createUser(email,name);
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/checkPurpose")
    public Optional<PurposeModel> checkPurpose(@RequestParam String purpose, @RequestParam Long userId){
        return purposeService.findPurposeRecords(purpose,userId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/createPurpose")
    public Optional<PurposeModel> createPurpose(@RequestParam String purpose, @RequestParam Long userId){
        return purposeService.createAPurpose(purpose,userId);
    }

}
