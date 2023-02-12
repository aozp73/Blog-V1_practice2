package shop.mtcoding.blogv1_2.dto.board;

import lombok.Getter;
import lombok.Setter;

public class BoardResp {

    @Getter
    @Setter
    public static class BoardMainRespDto {
        private int boardId;
        private String title;
        private String thumbnail;
        private int userId;
        private String username;
    }
}
