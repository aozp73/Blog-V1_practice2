package shop.mtcoding.blogv1_2.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_2.customException.ex.CustomApiException;
import shop.mtcoding.blogv1_2.dto.board.BoardReq.BoardSaveReqDto;
import shop.mtcoding.blogv1_2.model.Board;
import shop.mtcoding.blogv1_2.model.BoardRepository;
import shop.mtcoding.blogv1_2.util.HtmlParse;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void 게시글삭제(int boardId, int principalId) {

    }

    public void 게시글등록(BoardSaveReqDto boardSaveReqDto, int principalId) {
        String thumbnail = HtmlParse.thumbnail(boardSaveReqDto.getContent());
        try {
            boardRepository.insert(boardSaveReqDto.getTitle(), boardSaveReqDto.getContent(), thumbnail, principalId);
        } catch (Exception e) {
            throw new CustomApiException("일시적인 서버문제가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
