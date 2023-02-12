package shop.mtcoding.blogv1_2.dto.reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyReq {

    @Getter
    @Setter
    public static class ReplySaveReqDto {
        private String content;
        private int boardId;
    }
}
