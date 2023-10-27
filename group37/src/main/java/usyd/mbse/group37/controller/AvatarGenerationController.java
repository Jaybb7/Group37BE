package usyd.mbse.group37.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import usyd.mbse.group37.service.StableHordeService;

@RestController
@RequestMapping("/avatar")
@CrossOrigin(origins = "http://localhost:4200")
public class AvatarGenerationController {
   private final StableHordeService stableHordeService;
   
   public AvatarGenerationController(StableHordeService stableHordeService) {
      this.stableHordeService = stableHordeService;
   }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/generate")
    public String generateAvatar(@RequestParam String image) {
        String line = stableHordeService.generate(image);
        return line;
    }
}
