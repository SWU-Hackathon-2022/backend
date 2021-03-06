package MOAI.moai.login.service;

import MOAI.moai.login.dto.LoginDTO;
import MOAI.moai.login.response.LoginRes;
import MOAI.moai.member.Member;
import MOAI.moai.member.type.MemberType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginServiceTest {

    @Autowired LoginService loginService;

    @Test
    void login() throws Exception {
        /**
         *  given : given condition
         */


        /**
         *  when  : execution
         */
        LoginRes loginRes = loginService.login(new MockHttpServletResponse(),
                new LoginDTO("token1", null, null, null));


        /**
         *  then  : result
         */
        assertThat(loginRes.getDtype() == MemberType.COMPOSER);


    }
}