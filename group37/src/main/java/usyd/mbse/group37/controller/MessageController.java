package usyd.mbse.group37.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import usyd.mbse.group37.model.SendMessageRequest;
import usyd.mbse.group37.service.MessageService;

import java.util.Map;

@Controller
@RequestMapping("/message")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/get-messages")
    public ResponseEntity<?> getMessages(@RequestParam Long userId) {
        return new ResponseEntity<>(
                Map.of("data", messageService.getMessages(userId)),
                HttpStatus.OK);

    }

    @PostMapping("/send-message")
    public ResponseEntity<?> sendMessage(@RequestBody SendMessageRequest request) throws Exception {
        messageService.sendMessage(request.getFromUser(),
                request.getToUser(),
                request.getMessageContent());
        return new ResponseEntity<>(Map.of("data", "Message sent successfully."), HttpStatus.OK);
    }
}
