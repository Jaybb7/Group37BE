package usyd.mbse.group37.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import usyd.mbse.group37.model.GetMessagesResponse;
import usyd.mbse.group37.model.Message;
import usyd.mbse.group37.model.UserModel;
import usyd.mbse.group37.repository.MessageRepository;
import usyd.mbse.group37.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class MessageService {

    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    public MessageService( UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public void sendMessage(Long fromUser, Long toUser, String messageContent) throws Exception {
        if (Objects.equals(fromUser, toUser)) {
            throw new Exception("Cannot send invite to yourself");
        }
        Optional<UserModel> senderUser = userRepository.findByUserId(fromUser);
        Optional<UserModel> receiverUser = userRepository.findByUserId(toUser);
        if (senderUser.isPresent() && receiverUser.isPresent()) {
            messageRepository.save(new Message(fromUser, toUser, messageContent));
        } else {
            throw new Exception("Invalid usernames provided");
        }
    }

    public List<GetMessagesResponse> getMessages(Long userId) {
        List<Message> messagesByUserId = messageRepository.findByToUser(userId);
        return messagesByUserId
                .stream()
                .map(e -> new GetMessagesResponse(
                        e.getMessageId(),
                        e.getFromUser(),
                        e.getMessageContent(),
                        e.getMessageTimestamp()
                ))
                .toList();
    }
}
