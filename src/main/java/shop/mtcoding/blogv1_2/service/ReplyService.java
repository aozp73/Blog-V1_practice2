package shop.mtcoding.blogv1_2.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_2.customException.ex.CustomApiException;
import shop.mtcoding.blogv1_2.dto.reply.ReplyReq.ReplySaveReqDto;
import shop.mtcoding.blogv1_2.model.Reply;
import shop.mtcoding.blogv1_2.model.ReplyRepository;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void 댓글쓰기(ReplySaveReqDto replySaveReqDto, int principalId) {
        try {
            replyRepository.insert(replySaveReqDto.getComment(), replySaveReqDto.getBoardId(), principalId);
        } catch (Exception e) {
            throw new CustomApiException("일시적인 서버문제가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void 댓글삭제(int replyId, int principalId) {
        // 댓글 존재유무
        Reply replyPS = replyRepository.findById(replyId);
        if (replyPS == null) {
            throw new CustomApiException("삭제할 게시물이 존재하지 않습니다");
        }

        // 댓글작성자, 로그인유저 비교
        if (replyPS.getUserId() != principalId) {
            throw new CustomApiException("댓글삭제 권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        try {
            replyRepository.deleteById(replyId);
        } catch (Exception e) {
            throw new CustomApiException("일시적인 서버문제가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
