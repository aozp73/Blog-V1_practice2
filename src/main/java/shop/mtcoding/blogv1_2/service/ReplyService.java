package shop.mtcoding.blogv1_2.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_2.dto.reply.ReplyReq.ReplySaveReqDto;
import shop.mtcoding.blogv1_2.model.ReplyRepository;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void 댓글쓰기(ReplySaveReqDto replySaveReqDto) {

    }
}
