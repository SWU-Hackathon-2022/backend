package MOAI.moai.member.controller;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponse;
import MOAI.moai.common.BaseResponseStatus;
import MOAI.moai.member.response.MyPageRes;
import MOAI.moai.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static MOAI.moai.common.login.LoginUser.getLoginMemberId;


@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/mypage")
    public BaseResponse<MyPageRes> mypage(HttpServletRequest request, HttpSession session) throws BaseException {
        Long loginMemberId = getLoginMemberId(request, session);
        if (loginMemberId == null) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }
        else {
            return new BaseResponse<>(memberService.getMyPageInfo(loginMemberId));
        }
    }

}
