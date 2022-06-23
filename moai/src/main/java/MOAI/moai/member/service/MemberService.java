package MOAI.moai.member.service;

import MOAI.moai.member.Member;
import MOAI.moai.member.MemberGenre;
import MOAI.moai.member.genre.Genre;
import MOAI.moai.member.repository.MemberGenreRepository;
import MOAI.moai.member.repository.MemberRepository;
import MOAI.moai.member.response.MyPageRes;
import MOAI.moai.music.Music;
import MOAI.moai.music.repository.MusicRepository;
import MOAI.moai.music.response.MyPageMusicRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberGenreRepository memberGenreRepository;
    private final MusicRepository musicRepository;

    public MyPageRes getMyPageInfo (Long memberId) {
        Optional<Member> findMember = memberRepository.findByMemberId(memberId);
        if (findMember.isEmpty()) {
            return null;
        }
        else {
            List<Music> musicList = musicRepository.findAllByComposer(findMember.get());
            List<MemberGenre> memberGenreList = memberGenreRepository.findAllByMember(findMember.get());
            List<Genre> genreList = new ArrayList<>();
            for (MemberGenre memberGenre : memberGenreList) {
                genreList.add(memberGenre.getGenre());
            }

            MyPageRes result = new MyPageRes();
            result.setMemberId(findMember.get().getMemberId());
            result.setNickName(findMember.get().getNickName());
            result.setProfileImgUrl(findMember.get().getProfileImgUrl());
            result.setGenreList(genreList);

            result.setMusicResList(MyPageMusicRes.createMyPageMusicResList(musicList));

            return result;
        }
    }


}
