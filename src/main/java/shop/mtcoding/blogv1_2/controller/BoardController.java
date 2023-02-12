package shop.mtcoding.blogv1_2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_2.customException.ex.CustomException;
import shop.mtcoding.blogv1_2.dto.RespDto;
import shop.mtcoding.blogv1_2.dto.board.BoardReq.BoardSaveReqDto;
import shop.mtcoding.blogv1_2.model.BoardRepository;
import shop.mtcoding.blogv1_2.model.User;
import shop.mtcoding.blogv1_2.service.BoardService;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;

    private final BoardService boardService;

    private final BoardRepository boardRepository;

    @GetMapping({ "/", "/main" })
    public String main(Model model) {
        model.addAttribute("dtos", boardRepository.findByAllWithUser());
        return "board/main";
    }

    @GetMapping("/board/saveForm") // 유효성검사 x, 인증 o, 권한 x
    public String saveForm() {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            throw new CustomException("로그인이 필요합니다");
        }
        return "board/saveForm";
    }

    @PostMapping("/board") // 유효성검사 o, 인증 o, 권한 x
    public ResponseEntity<?> save(@RequestBody BoardSaveReqDto boardSaveReqDto) {
        // 인증
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            throw new CustomException("로그인이 필요합니다");
        }

        // 유효성검사
        if (boardSaveReqDto.getTitle() == null || boardSaveReqDto.getTitle().isEmpty()) {
            throw new CustomException("제목을 입력하세요");
        }
        if (boardSaveReqDto.getContent() == null || boardSaveReqDto.getContent().isEmpty()) {
            throw new CustomException("내용을 입력하세요");
        }
        if (boardSaveReqDto.getTitle().length() > 100) {
            throw new CustomException("제목은 100자 이내여야 합니다");
        }
        boardService.게시글등록(boardSaveReqDto, user.getId());

        return new ResponseEntity<>(new RespDto<>(1, "게시글 등록완료", null), HttpStatus.CREATED);
    }

}
