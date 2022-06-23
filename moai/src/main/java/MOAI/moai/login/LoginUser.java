package MOAI.moai.login;


import MOAI.moai.member.Member;
import MOAI.moai.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Component
public class LoginUser {

    private static MemberRepository memberRepository;

    @Value("${googleTokenHeader}")
    private static String tokenHeader;

    @Autowired
    public LoginUser(MemberRepository memberRepository) {
        LoginUser.memberRepository = memberRepository;
    }

    public static Long getLoginMemberId(HttpServletRequest request) {
        try {
            Optional<Member> findMember = memberRepository.findByToken(request.getHeader(tokenHeader));
            if (findMember.isEmpty()) {
                return null;
            }
            return findMember.get().getMemberId();

        }
        catch (Exception e) {
            return null;
        }

    }

}
