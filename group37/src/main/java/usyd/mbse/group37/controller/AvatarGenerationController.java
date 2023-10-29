package usyd.mbse.group37.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


   //input parameter is a webp image encoded in base64. output is a url where the image is stored online temporarily
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public String generateAvatar(@RequestBody String image) {
        image = image.replaceAll("\"", "");

        String line = stableHordeService.generate(image);
        return line;
    }
}
