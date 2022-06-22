package MOAI.moai.music.service;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponseStatus;
import MOAI.moai.common.file.FileUpload;
import MOAI.moai.common.login.LoginUser;
import MOAI.moai.member.Member;
import MOAI.moai.member.repository.MemberRepository;
import MOAI.moai.music.Music;
import MOAI.moai.music.dto.CreateMusicDTO;
import MOAI.moai.music.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.Optional;

import static MOAI.moai.common.file.FileUpload.musicFileUpload;
import static MOAI.moai.common.file.FileUpload.musicThumbnailUpload;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicService {

    private final MusicRepository musicRepository;
    private final MemberRepository memberRepository;

    public Long createMusic(HttpServletRequest request, HttpSession session, CreateMusicDTO dto) throws BaseException{
        try {
            Long loginMemberId = LoginUser.getLoginMemberId(request, session);
            if (loginMemberId == null) {
                throw new BaseException(BaseResponseStatus.INVALID_USER);
            }
            Optional<Member> findMember = memberRepository.findByMemberId(loginMemberId);
            if (findMember.isEmpty()) {
                throw new BaseException(BaseResponseStatus.INVALID_USER);
            }

            //Optional<Member> findMember = memberRepository.findByMemberId(1L);


            Music music = Music.createMusic(findMember.get(), dto.getMusicName(), dto.getGenre(), dto.getIntroduction(),
                    dto.getHashTag(), musicFileUpload(dto.getMusicFile()), musicThumbnailUpload(dto.getMusicThumbnail()));

            musicRepository.save(music);
            return music.getMusicId();
        }
        catch (Exception e) {
            throw new BaseException(BaseResponseStatus.REQUEST_ERROR);
        }
    }
}
