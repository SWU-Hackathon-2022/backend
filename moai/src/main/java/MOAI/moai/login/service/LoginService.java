package MOAI.moai.login.service;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponseStatus;
import MOAI.moai.login.dto.LoginDTO;
import MOAI.moai.login.response.LoginRes;
import MOAI.moai.member.Member;
import MOAI.moai.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;

    public LoginRes login(HttpServletResponse response, HttpSession session, LoginDTO dto) throws BaseException {
        Optional<Member> findMember = memberRepository.findByLoginId(dto.getLoginId());
        if (findMember.isEmpty()) {
            throw new BaseException(BaseResponseStatus.USERS_WRONG_LOGIN_ID);
        }
        if (!findMember.get().getPassword().equals(dto.getPassword())) {
            throw new BaseException(BaseResponseStatus.USERS_WRONG_PASSWORD);
        }
        setCookie(session, findMember.get().getMemberId(), response);

        return new LoginRes(findMember.get().getMemberId(), findMember.get().getNickName(), findMember.get().getDtype());
    }


    private void setCookie(HttpSession session, Long memberId, HttpServletResponse response) {
        String key = setSession(session, memberId);
        Cookie cookie = new Cookie("id", key);
        // TODO : maxAge 설정
        response.addCookie(cookie);
    }
    private String setSession(HttpSession session, Long memberId) {
        String key = String.valueOf(UUID.randomUUID());
        session.setAttribute(key, memberId);

        return key;
    }


}
