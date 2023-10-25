package usyd.mbse.group37.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Data
@Table(name = "Message")
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "from_user", nullable = false)
    private String fromUser;

    @Column(name = "to_user", nullable = false)
    private String toUser;

    @Column(name = "message_content", nullable = false)
    private String messageContent;

    @Column(name = "message_timestamp", nullable = false)
    private String messageTimestamp;

    public Message(String fromUser, String toUser, String messageContent) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.messageContent = messageContent;
        this.messageTimestamp = Timestamp.from(Instant.now()).toString();
    }

}
