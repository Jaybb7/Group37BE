package usyd.mbse.group37.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import usyd.mbse.group37.model.Message;
import usyd.mbse.group37.model.PostModel;
import usyd.mbse.group37.model.UserProfileModel;

import java.util.Optional;

public interface PostRepository extends CrudRepository<PostModel, Integer> {
    @Query("SELECT e FROM PostModel e WHERE e.postId = :postId")
    Optional<PostModel> findByPostId(int postId);
}
