package shop.mtcoding.blogv1_2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final HttpSession session;

    private final ReplyService replyService;

    @PostMapping("/reply") // 유효성 검사 o, 인증 o, 권한 x
    public ResponseEntity<?> reply(@RequestBody ReplySaveReqDto replySaveReqDto) {
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("로그인이 필요합니다");
        }

        // 유효성 검사
        if (replySaveReqDto.getComment() == null || replySaveReqDto.getComment().isEmpty()) {
            throw new CustomApiException("댓글 내용을 입력하세요");
        }

        replyService.댓글쓰기(replySaveReqDto, principal.getId());

        return new ResponseEntity<>(new RespDto<>(1, "댓글작성 완료", null), HttpStatus.OK);
    }

    @DeleteMapping("/reply/{id}") // 유효성 검사 x, 인증 o, 권한 o
    public ResponseEntity<?> delete(@PathVariable int id) {
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("로그인이 필요합니다");
        }

        replyService.댓글삭제(id, principal.getId());

        return new ResponseEntity<>(new RespDto<>(1, "댓글삭제 완료", null), HttpStatus.OK);
    }
}
