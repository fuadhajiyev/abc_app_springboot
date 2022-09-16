package fuad.hajiyev.abc_app.dto_request;

import lombok.Data;

@Data
public class LikeCreateRequest {

    Long id;
    Long userId;
    Long postId;
}
