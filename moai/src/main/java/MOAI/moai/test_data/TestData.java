package MOAI.moai.test_data;

import MOAI.moai.member.Member;
import MOAI.moai.member.controller.MemberController;
import MOAI.moai.member.repository.MemberGenreRepository;
import MOAI.moai.member.repository.MemberRepository;
import MOAI.moai.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestData {

    private final InitService initService;

    @PostConstruct
    public void init() { initService.init(); }

    @Component
    @RequiredArgsConstructor
    static class InitService {
        private final MemberRepository memberRepository;

        @Transactional
        public void init() {
            //Member member = Member.createMember("COMPOSER", "/profileImg/")
        }
    }

}
