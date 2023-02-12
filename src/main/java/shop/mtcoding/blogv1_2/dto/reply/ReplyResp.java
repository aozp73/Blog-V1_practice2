package shop.mtcoding.blogv1_2.dto.reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyResp {

    @Getter
    @Setter
    public static class ReplyAllRespDto {
        private int replyId;
        private String comment;
        private int boardId;
        private int userId;
        private String username;
    }
}
