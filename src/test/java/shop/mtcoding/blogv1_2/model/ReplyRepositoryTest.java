package shop.mtcoding.blogv1_2.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv1_2.dto.reply.ReplyResp.ReplyAllRespDto;

@MybatisTest
public class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;

    // @Autowired
    ObjectMapper om;

    @Test
    public void replyAllGet_test() throws Exception {
        // given
        int boardId = 1;
        om = new ObjectMapper();

        // then
        List<ReplyAllRespDto> dtos = replyRepository.findByBoardIdWithUser(boardId);
        // String responseBody = om.writeValueAsString(dtos);
        // System.out.println("테스트" + responseBody);

        // when
        assertThat(dtos.get(1).getComment()).isEqualTo("댓글3");
    }
}
