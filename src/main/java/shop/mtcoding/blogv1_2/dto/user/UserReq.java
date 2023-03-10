package shop.mtcoding.blogv1_2.dto.user;

import lombok.Getter;
import lombok.Setter;

public class UserReq {

    @Getter
    @Setter
    public static class UserJoinReqDto {
        private String username;
        private String password;
        private String email;
    }

    @Getter
    @Setter
    public static class UserLoginReqDto {
        private String username;
        private String password;
    }
}
