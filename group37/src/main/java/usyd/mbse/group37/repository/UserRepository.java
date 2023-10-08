package usyd.mbse.group37.repository;

import org.springframework.data.repository.CrudRepository;
import usyd.mbse.group37.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
