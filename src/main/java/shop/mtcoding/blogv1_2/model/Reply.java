package shop.mtcoding.blogv1_2.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Reply {
    private int id;
    private String comment;
    private int userId;
    private int boardId;
    private Timestamp createdAt;
}
