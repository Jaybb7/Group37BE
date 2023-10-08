package usyd.mbse.group37.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "User")
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_address", nullable = false, unique = true)
    private String emailAddress;

    @Lob
    @Column(name = "profile_photo", columnDefinition = "MEDIUMBLOB")
    private byte[] profilePhoto;

    @Lob
    @Column(name = "status_photo", columnDefinition = "MEDIUMBLOB")
    private byte[] statusPhoto;

    @Column(name = "purpose", nullable = false)
    private String purpose;

    public User(long userId, String userName, String firstName, String lastName, String emailAddress, byte[] profilePhoto, byte[] statusPhoto, String purpose) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.profilePhoto = profilePhoto;
        this.statusPhoto = statusPhoto;
        this.purpose = purpose;
    }
}
