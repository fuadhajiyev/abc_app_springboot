package fuad.hajiyev.abc_app.dto_response;


import fuad.hajiyev.abc_app.entities.Like;
import fuad.hajiyev.abc_app.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    Long id;
    Long userId;
    String userName;
    String title;
    String text;
    List<LikeResponse> postLike;

    public PostResponse(Post entity, List<LikeResponse> likes) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.postLike = likes;
    }
}
