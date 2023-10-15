package usyd.mbse.group37.repository;

import org.springframework.data.repository.CrudRepository;
import usyd.mbse.group37.model.Message;
import usyd.mbse.group37.model.PurposeModel;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByToUser(Long userId);
}
