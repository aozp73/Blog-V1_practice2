package shop.mtcoding.blogv1_2.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv1_2.dto.board.BoardReq.BoardSaveReqDto;
import shop.mtcoding.blogv1_2.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BoardControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    MockHttpSession mockSession;

    @Autowired
    ObjectMapper om;

    @BeforeEach
    public void set() {
        User user = new User();
        user.setId(1);
        user.setUsername("cos");
        user.setPassword("1234");
        user.setEmail("cos@nate.com");
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", user);
    }

    @Test
    public void post_test() throws Exception {
        // given
        BoardSaveReqDto boardSaveReqDto = new BoardSaveReqDto();
        boardSaveReqDto.setTitle("테스트 제목");
        boardSaveReqDto.setContent("테스트 내용");
        String requestBody = om.writeValueAsString(boardSaveReqDto);

        // then
        ResultActions resultActions = mvc.perform(post("/board")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .session(mockSession));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        // System.out.println("테스트 : " + responseBody);

        // when
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.msg").value("게시글 등록완료"));
        // service test
    }
}
