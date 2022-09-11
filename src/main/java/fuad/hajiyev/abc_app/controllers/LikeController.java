package fuad.hajiyev.abc_app.controllers;

import fuad.hajiyev.abc_app.dto.LikeCreateRequest;
import fuad.hajiyev.abc_app.dto_response.LikeResponse;
import fuad.hajiyev.abc_app.entities.Comment;
import fuad.hajiyev.abc_app.entities.Like;
import fuad.hajiyev.abc_app.services.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    final LikeService likeService;

    @GetMapping
    public ResponseEntity<List<LikeResponse>> getAllLikes(
            @RequestParam Optional<Long> userId,
            @RequestParam Optional<Long> postId) {
        List<LikeResponse> likes = likeService.getAllLikesFromService(userId, postId);
        return new ResponseEntity(likes, HttpStatus.OK);
    }

    @GetMapping("/{likeId}")
    public ResponseEntity<Like> getOneLike(@PathVariable("likeId") Long id) {
        Like like = likeService.getOneLikeFromService(id);

        return new ResponseEntity(like, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Like> createOneLike(@RequestBody LikeCreateRequest like) {
        Like newLike = likeService.createOneLikeFromService(like);
        return new ResponseEntity(newLike, HttpStatus.OK);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity deleteOneLike(@PathVariable Long likeId) {

        likeService.deleteOneByIdFromService(likeId);
        return ResponseEntity.status(HttpStatus.OK).body("Like Successfully deleted");

    }


}
