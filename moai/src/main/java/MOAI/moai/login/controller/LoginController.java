package MOAI.moai.login.controller;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponse;
import MOAI.moai.login.LoginUser;
import MOAI.moai.login.dto.LoginDTO;
import MOAI.moai.login.response.LoginRes;
import MOAI.moai.login.service.LoginService;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public BaseResponse<LoginRes> login(@RequestBody LoginDTO dto, HttpServletResponse response, HttpSession session)
            throws BaseException {

        return new BaseResponse<>(loginService.login(response, session, dto));

    }

    @GetMapping("/login/test")
    public Long test(HttpServletRequest request, HttpSession session) {
        return LoginUser.getLoginMemberId(request, session);
    }
}
