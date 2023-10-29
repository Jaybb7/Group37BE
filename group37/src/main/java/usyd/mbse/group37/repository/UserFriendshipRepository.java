package usyd.mbse.group37.repository;

import org.springframework.data.repository.CrudRepository;
import usyd.mbse.group37.model.UserFriendship;
import usyd.mbse.group37.model.UserProfileModel;

import java.util.List;

public interface UserFriendshipRepository extends CrudRepository<UserFriendship, Long> {
    List<UserFriendship> findByUser(UserProfileModel userprofile);

    boolean existsByUserAndFriend(UserProfileModel userProfileModel, UserProfileModel userProfileModel1);

    void deleteByUserAndFriend(UserProfileModel user, UserProfileModel friend);
}