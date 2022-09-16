package fuad.hajiyev.abc_app.dto_response;


import fuad.hajiyev.abc_app.entities.Post;
import lombok.Data;

@Data
public class PostResponse {

    Long id;
    Long userId;
    String userName;
    String title;
    String text;

    public PostResponse(Post entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
    }
}
