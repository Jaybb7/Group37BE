package usyd.mbse.group37.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import usyd.mbse.group37.model.GetMessagesResponse;
import usyd.mbse.group37.model.Message;
import usyd.mbse.group37.model.UserModel;
import usyd.mbse.group37.repository.MessageRepository;
import usyd.mbse.group37.repository.UserRepository;
import usyd.mbse.group37.service.GCP.GCPService;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class MessageService {

    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    private final GCPService gCPService;

    public MessageService(UserRepository userRepository, MessageRepository messageRepository, GCPService gCPService) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.gCPService = gCPService;
    }

    public void sendMessage(String fromUser, String toUser, String messageContent) throws Exception {
        if (Objects.equals(fromUser, toUser)) {
            throw new Exception("Cannot send message to yourself");
        }
        UserModel senderUser = userRepository.findByUserNameWithinIgnoreCase(fromUser);
        UserModel receiverUser = userRepository.findByUserNameWithinIgnoreCase(toUser);
        if (senderUser!=null && receiverUser!=null) {
            if(gCPService.requestGCP(messageContent).getDocumentSentiment().getScore()<0.05){
                throw new Exception("You can do better for your impression");
            }
            messageRepository.save(new Message(fromUser, toUser, messageContent));
        } else {
            throw new Exception("Receiver Not Found");
        }
    }

    public List<GetMessagesResponse> getMessages(String username) {
        List<Message> messagesByUserId = messageRepository.findByToUser(username);
        return messagesByUserId
                .stream()
                .map(e -> new GetMessagesResponse(
                        e.getFromUser(),
                        e.getMessageContent(),
                        e.getMessageTimestamp()
                ))
                .toList();
    }
}
