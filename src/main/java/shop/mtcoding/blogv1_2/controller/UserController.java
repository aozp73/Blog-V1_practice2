package shop.mtcoding.blogv1_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_2.customException.ex.CustomException;
import shop.mtcoding.blogv1_2.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.blogv1_2.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.blogv1_2.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("/join") // 유효성 o, 인증 x, 권한 x
    public String join(UserJoinReqDto userJoinReqDto) {
        if (userJoinReqDto.getUsername() == null || userJoinReqDto.getUsername().isEmpty()) {
            throw new CustomException("아이디를 입력하세요");
        }
        if (userJoinReqDto.getPassword() == null || userJoinReqDto.getPassword().isEmpty()) {
            throw new CustomException("비밀번호를 입력하세요");
        }
        if (userJoinReqDto.getEmail() == null || userJoinReqDto.getEmail().isEmpty()) {
            throw new CustomException("이메일을 입력하세요");
        }

        userService.회원가입(userJoinReqDto);

        return "redirect:/loginForm";
    }

    @PostMapping("/login") // 유효성 o, 인증 x, 권한 x (+session 저장)
    public String login(UserLoginReqDto userLoginReqDto) {
        if (userLoginReqDto.getUsername() == null || userLoginReqDto.getUsername().isEmpty()) {
            throw new CustomException("아이디를 입력하세요");
        }
        if (userLoginReqDto.getPassword() == null || userLoginReqDto.getPassword().isEmpty()) {
            throw new CustomException("비밀번호를 입력하세요");
        }

        userService.로그인(userLoginReqDto);

        return "redirect:/";
    }

}
