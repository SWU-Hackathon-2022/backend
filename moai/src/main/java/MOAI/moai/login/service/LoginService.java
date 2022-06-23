package MOAI.moai.login.service;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponseStatus;
import MOAI.moai.login.response.LoginRes;
import MOAI.moai.member.Member;
import MOAI.moai.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;

    @Value("${googleTokenHeader}")
    private String tokenHeader;

    public LoginRes login(HttpServletRequest request) throws BaseException {
        try {
            Optional<Member> findMember = memberRepository.findByToken(request.getHeader(tokenHeader));
            if (findMember.isEmpty()) {
                // TODO : 회원가입 페이지로 이동
                throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
            }
            return new LoginRes(findMember.get().getMemberId(), findMember.get().getNickName(), findMember.get().getDtype());
        }
        catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }




    }
}
