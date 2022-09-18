package fuad.hajiyev.abc_app.services;

import java.util.*;
import java.util.stream.Collectors;

import fuad.hajiyev.abc_app.dto_request.PostCreateRequest;
import fuad.hajiyev.abc_app.dto_request.PostUpdateRequest;
import fuad.hajiyev.abc_app.dto_response.LikeResponse;
import fuad.hajiyev.abc_app.dto_response.PostResponse;
import fuad.hajiyev.abc_app.entities.Like;
import fuad.hajiyev.abc_app.entities.Post;
import fuad.hajiyev.abc_app.entities.User;
import fuad.hajiyev.abc_app.repos.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService {


    private PostRepository postRepository;
    private LikeService likeService;
    private UserService userService;

    public PostService(PostRepository postRepository,
                       UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Autowired
    public  void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

    public List<PostResponse> getPostsFromService(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        } else
            list = postRepository.findAll();

        return list.stream().map(p ->

                {
                    List<LikeResponse> likes = likeService.getAllLikesFromService(Optional.ofNullable(null), Optional.of(p.getId()));
                    return new PostResponse(p, likes);}).collect(Collectors.toList());
    }

    public Post getPostByIdFromService(Long postId) {

        return postRepository.findById(postId).orElse(null);

    }


    public Post createPostFromService(PostCreateRequest newPostDto) {
        User user = userService.getUserByIdFromService(newPostDto.getUserId());
        if (user == null) {
            return null;
        }
        Post toSave = new Post();
        toSave.setId(newPostDto.getId());
        toSave.setText(newPostDto.getText());
        toSave.setTitle(newPostDto.getTitle());
        toSave.setUser(user);

        return postRepository.save(toSave);
    }

    public Post updateOnePostFromService(Long postId, PostUpdateRequest postUpdate) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setTitle(postUpdate.getTitle());
            toUpdate.setText(postUpdate.getText());

            return postRepository.save(toUpdate);
        }
        return null;
    }

    public void deleteOnePostFromService(Long postId) {
        postRepository.deleteById(postId);

    }
}
