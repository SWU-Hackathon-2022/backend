package MOAI.moai.login;


import MOAI.moai.member.Member;
import MOAI.moai.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 *  로그인된 사용자를 반환하는 Component
 *  *****************************************************************
 *
 *  쿠키에 현재 로그인된 사용자의 token을 저장하는 방식으로 구현하려 했으나,
 *  서로 다른 도메인 간 통신 과정 중 Cookie의 Samesite 이슈를 파악, 이를 해결하지
 *  못했습니다.
 *  따라서 임시로 첫 번째 테스트 사용자를 반환하게 설계하였으며, 해당 쿠키 이슈를
 *  해결하면 바로 쿠키를 사용할 수 있도록 로직이 구현되어 있는 상태입니다.
 *
 *  *****************************************************************
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginUser {

    @Value("${googleTokenHeader}")
    private String tokenHeader;

    private final MemberRepository memberRepository;


    public Long getLoginMemberId(HttpServletRequest request) {
        try {
            return 1L;
            /*for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("id")) {
                    Optional<Member> findMember = memberRepository.findByToken(cookie.getValue());
                    if (findMember.isEmpty()) {
                        return null;
                    }
                    return findMember.get().getMemberId();
                }
            }
            return null;*/
            /*Optional<Member> findMember = memberRepository.findByToken(request.getHeader(tokenHeader));
            if (findMember.isEmpty()) {
                return null;
            }
            return findMember.get().getMemberId();*/

        }
        catch (Exception e) {
            return null;
        }

    }

}
