package MOAI.moai.member.service;

import MOAI.moai.member.response.MyPageRes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    void getMyPageInfo() throws Exception {
        /**
         *  given : given condition
         */


        /**
         *  when  : execution
         */
        MyPageRes myPageInfo = memberService.getMyPageInfo(1L);


        /**
         *  then  : result
         */
        assertThat(myPageInfo.getNickName().equals("피터"));

    }

}