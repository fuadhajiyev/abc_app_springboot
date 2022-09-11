package fuad.hajiyev.abc_app.repos;

import fuad.hajiyev.abc_app.entities.Comment;
import fuad.hajiyev.abc_app.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUserIdAndPostId(Long userId, Long postId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostId(Long postId);
}
