package fuad.hajiyev.abc_app.controllers;

import fuad.hajiyev.abc_app.dto_request.PostCreateRequest;
import fuad.hajiyev.abc_app.dto_request.PostUpdateRequest;
import fuad.hajiyev.abc_app.dto_response.PostResponse;
import fuad.hajiyev.abc_app.entities.Post;
import fuad.hajiyev.abc_app.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    /*   /posts?userId={userId}   */
     PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts(@RequestParam Optional<Long> userId) {

        List<PostResponse> posts = postService.getPostsFromService(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostCreateRequest PostDto) {

        Post post = postService.createPostFromService(PostDto);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getOnePost(@PathVariable Long postId) {

        Post post = postService.getPostByIdFromService(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdate) {

        Post post = postService.updateOnePostFromService(postId, postUpdate);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deleteOnePost(@PathVariable Long postId) {
        postService.deleteOnePostFromService(postId);
        return  ResponseEntity.status(HttpStatus.OK).body("Post Successfully deleted");
    }


}
