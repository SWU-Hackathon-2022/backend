package MOAI.moai.test_data;

import MOAI.moai.member.Member;
import MOAI.moai.member.controller.MemberController;
import MOAI.moai.member.genre.Genre;
import MOAI.moai.member.repository.MemberGenreRepository;
import MOAI.moai.member.repository.MemberRepository;
import MOAI.moai.member.service.MemberService;
import MOAI.moai.member.type.MemberType;
import MOAI.moai.music.Music;
import MOAI.moai.music.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import static MOAI.moai.member.Member.createMember;
import static MOAI.moai.music.Music.createMusic;

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
        private final MusicRepository musicRepository;

        @Transactional
        public void init() {
            // member 추가
            Member member1 = createMember(MemberType.COMPOSER, null, "user1", "pwd1", "김작곡",
                    "김작곡닉네임");
            Member member2 = createMember(MemberType.COMPOSER, null, "user2", "pwd2", "이작곡",
                    "이작곡닉네임");
            Member member3 = createMember(MemberType.ARTIST, null, "user3", "pwd3", "김아티스트",
                    "김아티스트닉네임");
            Member member4 = createMember(MemberType.ARTIST, null, "user4", "pwd4", "이아티스트",
                    "이아티스트닉네임");
            Member member5 = createMember(MemberType.ARTIST, null, "user5", "pwd5", "박아티스트",
                    "박아티스트닉네임");
            memberRepository.save(member1);
            memberRepository.save(member2);
            memberRepository.save(member3);
            memberRepository.save(member4);
            memberRepository.save(member5);

            // music 추가
            Music music1 = createMusic(member1, "노래이름1", Genre.BALLAD, "이것은 노래이름1 입니다!", "#발라드 #신남",
                    null, null);
            Music music2 = createMusic(member1, "노래이름2", Genre.POP, "이것은 노래이름2 입니다!", "#팝 #신남",
                    null, null);
            Music music3 = createMusic(member1, "노래이름3", Genre.ROCK, "이것은 노래이름3 입니다!", "#락 #신남",
                    null, null);
            Music music4 = createMusic(member2, "노래이름4", Genre.DANCE, "이것은 노래이름4 입니다!", "#댄스 #신남",
                    null, null);
            Music music5 = createMusic(member2, "노래이름5", Genre.DISCO, "이것은 노래이름5 입니다!", "#디스코 #신남",
                    null, null);
            musicRepository.save(music1);
            musicRepository.save(music2);
            musicRepository.save(music3);
            musicRepository.save(music4);
            musicRepository.save(music5);

        }
    }

}
