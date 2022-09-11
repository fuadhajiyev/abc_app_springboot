package fuad.hajiyev.abc_app.services;


import fuad.hajiyev.abc_app.dto.PostCreateRequest;
import fuad.hajiyev.abc_app.dto.PostUpdateRequest;
import fuad.hajiyev.abc_app.entities.Post;
import fuad.hajiyev.abc_app.entities.User;
import fuad.hajiyev.abc_app.repos.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    final PostRepository postRepository;
    final UserService userService;


    public List<Post> getPostsFromService(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public Post getPostByIdFromService(Long postId) {

        return postRepository.findById(postId).orElse(null);

    }


    public Post createPostFromService(PostCreateRequest newPostDto) {
        User user = userService.getUserByIdFromService(newPostDto.getId());
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
        if(post.isPresent()) {
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
