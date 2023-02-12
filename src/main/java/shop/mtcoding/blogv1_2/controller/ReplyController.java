package shop.mtcoding.blogv1_2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_2.customException.ex.CustomApiException;
import shop.mtcoding.blogv1_2.dto.RespDto;
import shop.mtcoding.blogv1_2.dto.reply.ReplyReq.ReplySaveReqDto;
import shop.mtcoding.blogv1_2.model.User;
import shop.mtcoding.blogv1_2.service.ReplyService;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private HttpSession session;

    private ReplyService replyService;

    @PostMapping("/reply") // 유효성 검사 o, 인증 o, 권한 x
    public ResponseEntity<?> delete(@RequestBody ReplySaveReqDto replySaveReqDto) {
        // 인증
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            throw new CustomApiException("로그인이 필요합니다");
        }

        // 유효성 검사
        if (replySaveReqDto.getContent() == null || replySaveReqDto.getContent().isEmpty()) {
            throw new CustomApiException("댓글 내용을 입력하세요");
        }

        replyService.댓글쓰기(replySaveReqDto);

        return new ResponseEntity<>(new RespDto<>(1, "댓글작성 완료", null), HttpStatus.OK);
    }
}
