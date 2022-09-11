package fuad.hajiyev.abc_app.services;

import fuad.hajiyev.abc_app.dto.CommentCreateRequest;
import fuad.hajiyev.abc_app.dto.CommentUpdateRequest;
import fuad.hajiyev.abc_app.entities.Comment;
import fuad.hajiyev.abc_app.entities.Post;
import fuad.hajiyev.abc_app.entities.User;
import fuad.hajiyev.abc_app.repos.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {

    final CommentRepository commentRepository;
    final UserService userService;
    final PostService postService;

    public List<Comment> getAllCommentsFromService(Optional<Long> userId, Optional<Long> postId) {


        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else {
            return commentRepository.findAll();
        }


    }

    public Comment getOneCommentFromService(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NullPointerException("not found comment ."));
        return comment;
    }

    public Comment createOneCommentFromService(CommentCreateRequest creatComment) {
        User user = userService.getUserByIdFromService(creatComment.getUserId());
        Post post = postService.getPostByIdFromService(creatComment.getPostId());

        if (user != null && post != null) {

            Comment commentToSave = new Comment();
            commentToSave.setId(commentToSave.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(creatComment.getText());

            commentRepository.save(commentToSave);
        }

        return null;

    }

    public Comment updateOneCommentByIdFromService(Long commentId, CommentUpdateRequest updateRequest) {
        Optional<Comment> comment =  commentRepository.findById(commentId);

        if (comment.isPresent()){
            Comment commentToUpdate =  comment.get();
            commentToUpdate.setText(updateRequest.getText());
            return  commentRepository.save(commentToUpdate);
        }

        return null;

    }

    public void deleteOneCommentByIdFromService(Long commentId) {
       Optional<Comment> comment = commentRepository.findById(commentId);
       if (comment.isPresent()){
           commentRepository.deleteById(comment.get().getId());
       }
         throw  new NullPointerException("Comment not Found !");
    }
}
