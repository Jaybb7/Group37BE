package usyd.mbse.group37.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "UserProfile")
public class UserProfileModel {
    @Id
    @Column(name = "userProfileId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userProfileId;

    @Column(name = "location")
    private String location;

    @Column(name = "address")
    private String address;

    @Column(name = "linkedInLink")
    private String linkedInLink;

    @Column(name = "bio")
    private String bio;

    @Column(name = "twitterLink")
    private String twitterLink;

    @Column(name = "instagramLink")
    private String instagramLink;

    @Column(name = "birthDate")
    private String birthDate;

    @Column(name = "currentPurpose")
    private String currentPurpose;

    @Lob
    @Column(name = "profilePhoto", columnDefinition = "BLOB")
    private byte[] profilePhoto;

    @Lob
    @Column(name = "statusPhoto", columnDefinition = "BLOB")
    private byte[] statusPhoto;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserModel userModel;


}

