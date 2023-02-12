package shop.mtcoding.blogv1_2.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_2.dto.board.BoardReq.BoardSaveReqDto;
import shop.mtcoding.blogv1_2.model.BoardRepository;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void 게시글등록(BoardSaveReqDto boardSaveReqDto, int principalId) {

        // boardRepository.insert(null, null, null, principalId)
    }

}
