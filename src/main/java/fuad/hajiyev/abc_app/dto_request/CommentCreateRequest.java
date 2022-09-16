package fuad.hajiyev.abc_app.dto_request;


import lombok.Data;

@Data
public class CommentCreateRequest {

    Long id;
    Long userId;
    Long postId;
    String text;
}
