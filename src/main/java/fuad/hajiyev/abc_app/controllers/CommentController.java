package fuad.hajiyev.abc_app.controllers;

import fuad.hajiyev.abc_app.dto.CommentCreateRequest;
import fuad.hajiyev.abc_app.dto.CommentUpdateRequest;
import fuad.hajiyev.abc_app.entities.Comment;
import fuad.hajiyev.abc_app.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    final CommentService commentService;


    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments(@RequestParam Optional<Long> userId,
                                                        @RequestParam Optional<Long> postId){
        List<Comment> comments =  commentService.getAllCommentsFromService(userId, postId);

        return new  ResponseEntity(comments, HttpStatus.OK);

    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getOneComment(@PathVariable("commentId") Long id){
     Comment comment =  commentService.getOneCommentFromService(id);

        return new  ResponseEntity(comment, HttpStatus.OK);

    }

    @PostMapping
    public  ResponseEntity<Comment> createOneComment(@RequestBody CommentCreateRequest creatComment){
        Comment newComment = commentService.createOneCommentFromService(creatComment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest updateRequest){
        Comment updatedComment =commentService.updateOneCommentByIdFromService(commentId, updateRequest);
        return  new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteOneComment(@PathVariable Long commentId){

            commentService.deleteOneCommentByIdFromService(commentId);

        return  ResponseEntity.status(HttpStatus.OK).body("Comment Successfully deleted");
    }

}
