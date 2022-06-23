package MOAI.moai.music.controller;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponse;
import MOAI.moai.common.BaseResponseStatus;
import MOAI.moai.member.genre.Genre;
import MOAI.moai.music.dto.CreateMusicDTO;
import MOAI.moai.music.response.MainPageMusicRes;
import MOAI.moai.music.service.MusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  Music 관련 Controller
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class MusicController {

    private final MusicService musicService;

    /**
     *
     *  ****************************************************
     *
     *  TODO : 현재 해당 API에 CORS 이슈가 지속적으로 발견되어서 조치중입니다.
     *  로컬 환경에서 정상적으로 테스트 확인했지만, Spring과 React 간 연동 과정 중 CORS 이슈를 발견했습니다.
     *
     *  ****************************************************
     */
    @PostMapping("/music/new")
    public BaseResponse<String> createNewComposerMusic(HttpServletRequest request, CreateMusicDTO dto) throws BaseException {
        musicService.createMusic(request, dto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    /**
     *
     *  music/new API test ver.
     */
    @PostMapping("/music/new/test")
    public BaseResponse<String> createNewComposerMusic(
            @RequestPart("files") List<MultipartFile> files,
            @RequestParam String musicName,
            @RequestParam String introduction,
            @RequestParam String hashTag,
            @RequestParam Genre genre,
            HttpServletRequest request
            ) throws BaseException {
        log.info("컨트롤러 진입");
        musicService.createMusicV2(request, files, musicName, introduction, hashTag, genre);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }


    /**
     *
     *  1개의 곡 상세
     */
    @GetMapping("/music/{musicId}")
    public BaseResponse<MainPageMusicRes> getMainPageMusicRes(@PathVariable Long musicId, HttpServletRequest request)
            throws BaseException{
        return new BaseResponse<>(musicService.getMainPageMusicRes(request, musicId));
    }
}
