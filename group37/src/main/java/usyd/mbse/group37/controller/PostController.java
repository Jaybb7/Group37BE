package usyd.mbse.group37.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import usyd.mbse.group37.model.PostModel;
import usyd.mbse.group37.model.UserModel;
import usyd.mbse.group37.repository.PostRepository;
import usyd.mbse.group37.service.PostService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("post")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/setPost")
    public ResponseEntity<?> addNewPost(@RequestParam String postTitle,
                                        @RequestParam String postBenefits,
                                        @RequestParam String postSalary,
                                        @RequestParam String desc,
                                        @RequestParam String requirements,
                                        @RequestParam String postLocation,
                                        @RequestParam String postPointOfContact,
                                        @RequestParam String userId
    ) {
        PostModel newPost = postService.createPost(postTitle, postBenefits, postSalary, Long.valueOf(userId), postLocation, postPointOfContact, desc, requirements);

        return new ResponseEntity<>(Map.of("data", "Post created successfully."), HttpStatus.CREATED);
    }

    @GetMapping("/getPost")
    public ResponseEntity<?> getAllPosts() {
        List<PostModel> posts = postService.getAllPosts();
        return new ResponseEntity<>(Map.of("data", posts), HttpStatus.OK);
    }
    @GetMapping("/getPost/{postId}")
    public ResponseEntity<?> getPostById(@RequestParam int postId) {
        PostModel post = postService.getPostById(postId);

        if (post == null) {
            return new ResponseEntity<>(Map.of("error", "Post not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(Map.of("data", post), HttpStatus.OK);
    }
}
