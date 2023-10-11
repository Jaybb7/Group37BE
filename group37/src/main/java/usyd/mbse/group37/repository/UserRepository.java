package usyd.mbse.group37.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import usyd.mbse.group37.model.UserModel;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserModel, String> {
    @Query("SELECT e FROM UserModel e WHERE e.userId = :userId")
    Optional<UserModel> findByUserId(Long userId);
}
