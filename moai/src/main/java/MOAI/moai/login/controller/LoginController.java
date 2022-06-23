package MOAI.moai.login.controller;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponse;
import MOAI.moai.login.LoginUser;
import MOAI.moai.login.dto.LoginDTO;
import MOAI.moai.login.response.LoginRes;
import MOAI.moai.login.service.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *  Login 담당 Controller
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;
    private final LoginUser loginUser;

    /**
     *
     *  로그인
     */
    @PostMapping("/login")
    public BaseResponse<LoginRes> login(HttpServletResponse response, @RequestBody LoginDTO dto)
            throws BaseException {

        return new BaseResponse<>(loginService.login(response, dto));

    }

    /**
     *
     *  로그인 테스트 (for Test)
     */
    @GetMapping("/login/test")
    public Long test(HttpServletRequest request) {
        return loginUser.getLoginMemberId(request);
    }
}
