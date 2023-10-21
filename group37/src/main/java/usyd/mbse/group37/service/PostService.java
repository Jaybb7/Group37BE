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
