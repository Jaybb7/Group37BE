package usyd.mbse.group37.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/")
public class UserController {

    @GetMapping
    public ResponseEntity<String> createUser(){
        return ResponseEntity.ok("User Created");
    }

}
