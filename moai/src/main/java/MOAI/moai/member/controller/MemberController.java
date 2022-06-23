package MOAI.moai.member.controller;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponse;
import MOAI.moai.common.BaseResponseStatus;
import MOAI.moai.login.LoginUser;
import MOAI.moai.member.response.MyPageRes;
import MOAI.moai.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *  Member 관련 Controller
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final LoginUser loginUser;

    /**
     *
     *  마이페이지 API
     *  현재 로그인된 사용자를 request에서 추출 후 해당 사용자의 정보 반환
     */
    @GetMapping("/mypage")
    public BaseResponse<MyPageRes> mypage(HttpServletRequest request) throws BaseException {
        Long loginMemberId = loginUser.getLoginMemberId(request);
        if (loginMemberId == null) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }
        else {
            return new BaseResponse<>(memberService.getMyPageInfo(loginMemberId));
        }
    }

}
