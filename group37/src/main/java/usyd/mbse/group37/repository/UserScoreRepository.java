package usyd.mbse.group37.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import usyd.mbse.group37.model.UserScoreModel;

import java.util.Optional;

public interface UserScoreRepository extends CrudRepository<UserScoreModel, String> {
    @Query("SELECT e FROM UserScoreModel e WHERE e.scoreId = :scoreId")
    Optional<UserScoreModel> findByScoreId(Long scoreId);
}
