package usyd.mbse.group37.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "User", indexes = {
        @Index(name = "index_email_address", columnList = "email_address"),
        @Index(name = "index_user_id", columnList = "user_id")
})
@NoArgsConstructor
public class UserModel {


    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Id
    @Column(name = "email_address")
    private String emailAddress;

    @Lob
    @Column(name = "profile_photo", columnDefinition = "MEDIUMBLOB")
    private byte[] profilePhoto;

    @Lob
    @Column(name = "status_photo", columnDefinition = "MEDIUMBLOB")
    private byte[] statusPhoto;

}
