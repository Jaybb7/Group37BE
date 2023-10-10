package usyd.mbse.group37.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Purpose")
@NoArgsConstructor
public class PurposeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purpose_id")
    private long purposeId;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "purpose_score")
    private double purposeScore;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserModel UserModel;

}
