package shop.mtcoding.blogv1_2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RespDto<T> {
    private int code;
    private String msg;
    private T data;
}
