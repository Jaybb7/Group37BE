package usyd.mbse.group37.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usyd.mbse.group37.exception.PostNotFoundException;
import usyd.mbse.group37.model.PostModel;
import usyd.mbse.group37.model.UserModel;
import usyd.mbse.group37.repository.PostRepository;
import usyd.mbse.group37.repository.UserRepository;


import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public String getPostBenefits(int postId) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            return postModel.getPostBenefits();
        } else {
            return "Post benefits not found";
        }
    }

    public String getPostTitle(int postId) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            return postModel.getPostTitle();
        } else {
            return "Post title not found";
        }
    }

    public void setPostSalary(int postId, String salary) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            postModel.setPostSalary(salary);
            postRepository.save(postModel);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public void setPostBenefits(int postId, String benefits) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            postModel.setPostBenefits(benefits);
            postRepository.save(postModel);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public void setPostPointOfContact(int postId, String pointOfContact) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            postModel.setPostPointOfContact(pointOfContact);
            postRepository.save(postModel);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public String getPostRequirements(int postId) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            return postModel.getPostRequirements();
        } else {
            return "Post requirement not found";
        }
    }

    public String getPostPointOfContact(int postId) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            return postModel.getPostPointOfContact();
        } else {
            return "Post point of contact not found";
        }
    }

    public String getPostLocation(int postId) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            return postModel.getPostLocation();
        } else {
            return "Post location not found";
        }
    }

    //public int getPostId()

    public void setPostTitle(int postId, String title) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            postModel.setPostTitle(title);
            postRepository.save(postModel);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public void setPostDesc(int postId, String Desc) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            postModel.setPostDesc(Desc);
            postRepository.save(postModel);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public void setPostId(int postId) {

    }

    public void setPostRequirements(String postRequirements, int postId) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            postModel.setPostRequirements(postRequirements);
            postRepository.save(postModel);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public String getPostDesc(int postId) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            return postModel.getPostDesc();
        } else {
            return "Post Desc not found";
        }
    }

    public void setPostLocation(int postId, String postLocation) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            postModel.setPostLocation(postLocation);
            postRepository.save(postModel);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public String getPostSalary(int postId) {
        Optional<PostModel> postOptional = postRepository.findByPostId(postId);

        if (postOptional.isPresent()) {
            PostModel postModel = postOptional.get();
            return postModel.getPostSalary();
        } else {
            return "Post salary not found";
        }
    }

    public PostModel createPost(String postTitle, String postBenefits, String postSalary, Long userId, String postLocation, String postPointOfContact, String desc, String requirements) {
        UserModel user = userRepository.findById(String.valueOf(userId)).orElseThrow(() -> new RuntimeException("User not found"));
        PostModel postModel = new PostModel();

        postModel.setPostTitle(postTitle);
        postModel.setPostBenefits(postBenefits);
        postModel.setUser(user);
        postModel.setPostDesc(desc);
        postModel.setPostLocation(postLocation);
        postModel.setPostRequirements(requirements);
        postModel.setPostPointOfContact(postPointOfContact);
        postModel.setPostSalary(postSalary);


        postRepository.save(postModel);

        return postModel;
    }

    public List<PostModel> getAllPosts() {
        return (List<PostModel>) postRepository.findAll();
    }
    public PostModel getPostById(int postId) {
        return postRepository.findByPostId(postId).orElse(null);
    }
}
