package MOAI.moai.music.service;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponseStatus;
import MOAI.moai.login.LoginUser;
import MOAI.moai.member.Member;
import MOAI.moai.member.genre.Genre;
import MOAI.moai.member.repository.MemberRepository;
import MOAI.moai.member.type.MemberType;
import MOAI.moai.music.Music;
import MOAI.moai.music.dto.CreateMusicDTO;
import MOAI.moai.music.repository.MusicQueryRepository;
import MOAI.moai.music.repository.MusicRepository;
import MOAI.moai.music.response.MainPageMusicRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static MOAI.moai.common.file.FileUpload.musicFileUpload;
import static MOAI.moai.common.file.FileUpload.musicThumbnailUpload;

/**
 *  Music 관련 Service
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MusicService {

    private final MusicRepository musicRepository;
    private final MemberRepository memberRepository;
    private final MusicQueryRepository musicQueryRepository;
    private final LoginUser loginUser;

    /**
     *
     *  Music 생성 메서드
     */
    public Long createMusic(HttpServletRequest request, CreateMusicDTO dto) throws BaseException{
        try {
            // 로그인된 사용자 판단
            Long loginMemberId = loginUser.getLoginMemberId(request);
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

    /**
     *
     *  for Test
     */
    public Long createMusicV2(HttpServletRequest request,
                              List<MultipartFile> files,
                              String musicName,
                              String introduction,
                              String hashTag,
                              Genre genre) throws BaseException{
        try {
            log.info("files 크기 : {}", files.size());
            log.info("musicName : {}", musicName);

            Long loginMemberId = loginUser.getLoginMemberId(request);
            if (loginMemberId == null) {
                throw new BaseException(BaseResponseStatus.INVALID_USER);
            }
            Optional<Member> findMember = memberRepository.findByMemberId(loginMemberId);
            if (findMember.isEmpty()) {
                throw new BaseException(BaseResponseStatus.INVALID_USER);
            }
            MultipartFile musicThumbnail = null;
            MultipartFile musicFile = null;

            // file 분류
            for (MultipartFile file : files) {
                String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
                if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("gif") || extension.equals("png")
                        || extension.equals("tiff") || extension.equals("svg") || extension.equals("bmp")) {
                    musicThumbnail = file;
                }
                else {
                    musicFile = file;
                }
            }

            Music music = Music.createMusic(findMember.get(), musicName, genre, introduction,
                    hashTag, musicFileUpload(musicFile), musicThumbnailUpload(musicThumbnail));

            musicRepository.save(music);
            return music.getMusicId();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseStatus.REQUEST_ERROR);
        }
    }

    /**
     *
     *  메인페이지에 등장하는 곡 response 객체
     */
    public MainPageMusicRes getMainPageMusicRes (HttpServletRequest request, Long musicId) throws BaseException{
        Long loginMemberId = loginUser.getLoginMemberId(request);
        if (loginMemberId == null) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }
        Optional<Member> findMember = memberRepository.findByMemberId(loginMemberId);
        if (findMember.isEmpty() || findMember.get().getDtype() != MemberType.COMPOSER) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }

        //Optional<Member> findMember = memberRepository.findByMemberId(1L);


        Music findMusic = musicQueryRepository.findMusicBySequence(musicId);
        if (findMusic == null) {
            throw new BaseException(BaseResponseStatus.REQUEST_ERROR);
        }

        return new MainPageMusicRes(findMusic.getThumbnailUrl(), findMusic.getFileUrl(), findMusic.getComposer().getMemberThumbnailUrl(),
                findMusic.getComposer().getNickName(), findMusic.getIntroduction(), findMusic.getGenre(), findMusic.getHashTag());
    }
}
