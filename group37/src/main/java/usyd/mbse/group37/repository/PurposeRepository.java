package usyd.mbse.group37.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import usyd.mbse.group37.model.PurposeModel;

import java.util.Optional;

public interface PurposeRepository extends CrudRepository<PurposeModel, Long> {
    @Query("SELECT e FROM PurposeModel e WHERE e.purpose = :purpose AND e.UserModel = :userId")
    Optional<PurposeModel> findByPurposeAndUserID(String purpose, Long userId);
}
