package usyd.mbse.group37.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_friends")
public class UserFriendship {

    @Id
    @Column(name = "friendship_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendshipId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserProfileModel user;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private UserProfileModel friend;

    @Enumerated(EnumType.STRING)
    @Column(name = "friendship_type")
    private FriendshipType friendshipType;

    public UserFriendship() {
    }
    public enum FriendshipType {
        BUSINESS, DATING, SOCIAL, OTHER;
    }
}
