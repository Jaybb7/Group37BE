package usyd.mbse.group37.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import usyd.mbse.group37.model.PurposeModel;
import usyd.mbse.group37.model.UserModel;
import usyd.mbse.group37.model.UserProfileModel;

import java.util.Optional;

public interface UserProfileRepository extends CrudRepository<UserProfileModel, Long> {
    @Query("SELECT e FROM UserProfileModel e WHERE e.userProfileId = :userProfileId")
    Optional<UserProfileModel> findByUserProfileId(Long userProfileId);

}
