package shop.mtcoding.blogv1_2.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_2.customException.ex.CustomApiException;
import shop.mtcoding.blogv1_2.dto.board.BoardReq.BoardSaveReqDto;
import shop.mtcoding.blogv1_2.dto.board.BoardResp.BoardUpdateRespDto;
import shop.mtcoding.blogv1_2.model.Board;
import shop.mtcoding.blogv1_2.model.BoardRepository;
import shop.mtcoding.blogv1_2.util.HtmlParse;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void 게시글수정(BoardUpdateRespDto boardUpdateRespDto, int principalId) {
        // 수정할 게시글 존재유무
        System.out.println("테스트" + boardUpdateRespDto.getBoardId());
        Board boardPS = boardRepository.findById(boardUpdateRespDto.getBoardId());
        if (boardPS == null) {
            throw new CustomApiException("수정할 게시물이 존재하지 않습니다");
        }

        // 게시물 작성자, 로그인 유저 동일유무
        if (boardPS.getUserId() != principalId) {
            throw new CustomApiException("수정권한이 없습니다");
        }

        String thumbnail = HtmlParse.thumbnail(boardUpdateRespDto.getContent());

        try {
            boardRepository.updateById(boardUpdateRespDto.getBoardId(), boardUpdateRespDto.getTitle(),
                    boardUpdateRespDto.getContent(), thumbnail);
        } catch (Exception e) {
            throw new CustomApiException("일시적인 서버문제가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void 게시글삭제(int boardId, int principalId) {
        // 삭제할 게시글 존재유무
        Board boardPS = boardRepository.findById(boardId);
        if (boardPS == null) {
            throw new CustomApiException("삭제할 게시물이 존재하지 않습니다");
        }

        // 게시물 작성자, 로그인 유저 동일유무
        if (boardPS.getUserId() != principalId) {
            throw new CustomApiException("삭제권한이 없습니다");
        }

        try {
            boardRepository.deleteById(boardId);
        } catch (Exception e) {
            throw new CustomApiException("일시적인 서버문제가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
