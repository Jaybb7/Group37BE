package usyd.mbse.group37.model;

import jakarta.persistence.*;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;

@Data
@Entity
@Table(name = "Post", indexes = {
        @Index(name = "index_user_email", columnList = "user_email")
})
public class PostModel {
    @Id
    @Column(name = "postId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(name = "postBenefits")
    private String postBenefits;

    @Column(name = "postSalary")
    private String postSalary;

    @Column(name = "postDesc")
    private String postDesc;

    @Column(name = "postTitle")
    private String postTitle;

    @Column(name = "postLocation")
    private String postLocation;

    @Column(name = "postPointOfContact")
    private String postPointOfContact;

    @Column(name = "postRequirements")
    private String postRequirements;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email_address")
    private UserModel user;
}
