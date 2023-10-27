package usyd.mbse.group37.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "UserScore")
public class UserScoreModel {
    @Id
    @Column(name = "scoreId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long scoreId;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "score")
    private String score;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserModel userModel;
}
