package MOAI.moai.login.service;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponseStatus;
import MOAI.moai.login.dto.LoginDTO;
import MOAI.moai.login.response.LoginRes;
import MOAI.moai.member.Member;
import MOAI.moai.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


/**
 *  Login 로직 담당 service
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;

    @Value("${googleTokenHeader}")
    private String tokenHeader;

    public LoginRes login(HttpServletResponse response, LoginDTO dto) throws BaseException {
        try {
            // LoginDTO로 부터 넘겨받은 Google OAuth Token을 가지고 로그인된 사용자가 있는지 확인
            // TODO : 로그인된 사용자를 판단하기 위해 추가적으로 Redis 도입 고려
            Optional<Member> findMember = memberRepository.findByToken(dto.getToken());
            if (findMember.isEmpty()) {
                // TODO : 존재하지 않는 회원이면 회원가입 페이지로 리다이렉트
                throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
            }

            // Cookie 생성 및 token을 저장
            ResponseCookie cookie = ResponseCookie.from("id", dto.getToken())
                    .path("/")
                    .secure(true)
                    .sameSite("None")
                    .httpOnly(true)
                    .build();

            response.setHeader("Set-cookie", cookie.toString());

            return new LoginRes(findMember.get().getMemberId(), findMember.get().getNickName(), findMember.get().getDtype());
        }
        catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }




    }
}
