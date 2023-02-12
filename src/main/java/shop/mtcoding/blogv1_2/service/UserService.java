package shop.mtcoding.blogv1_2.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blogv1_2.customException.ex.CustomException;
import shop.mtcoding.blogv1_2.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.blogv1_2.model.User;
import shop.mtcoding.blogv1_2.model.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void 회원가입(UserJoinReqDto userJoinReqDto) {
        User user = userRepository.findByUsername(userJoinReqDto.getUsername());
        // 동일한 유저네임 존재 확인
        if (user != null) {
            throw new CustomException("동일한 아이디가 존재합니다");
        }

        try {
            userRepository.insert(userJoinReqDto.getUsername(), userJoinReqDto.getPassword(),
                    userJoinReqDto.getEmail());
        } catch (Exception e) {
            throw new CustomException("일시적인 서버문제가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
