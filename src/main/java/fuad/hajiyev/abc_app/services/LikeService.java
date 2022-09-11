package fuad.hajiyev.abc_app.services;


import fuad.hajiyev.abc_app.dto.LikeCreateRequest;
import fuad.hajiyev.abc_app.dto_response.LikeResponse;
import fuad.hajiyev.abc_app.entities.Like;
import fuad.hajiyev.abc_app.entities.Post;
import fuad.hajiyev.abc_app.entities.User;
import fuad.hajiyev.abc_app.repos.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LikeService {

    final LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public List<LikeResponse> getAllLikesFromService(Optional<Long> uId, Optional<Long> pId) {
        List<Like> list;

        if (uId.isPresent() && pId.isPresent()) {
            list = likeRepository.findByUserIdAndPostId(uId.get(), pId.get());
        } else if (uId.isPresent()) {
            list = likeRepository.findByUserId(uId.get());
        } else if (pId.isPresent()) {
            list = likeRepository.findByPostId(pId.get());
        } else {
            list = likeRepository.findAll();
        }

        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }


    public Like getOneLikeFromService(Long id) {
        return likeRepository.findById(id).orElse(null);
    }


    public Like createOneLikeFromService(LikeCreateRequest like) {
        User user = userService.getUserByIdFromService(like.getUserId());
        Post post = postService.getPostByIdFromService(like.getPostId());
        if(user != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setId(like.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        }else
            return null;


    }

    public void deleteOneByIdFromService(Long likeId) {
    likeRepository.findById(likeId).orElse(null);
    }
}
