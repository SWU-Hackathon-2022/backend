package MOAI.moai.music.controller;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponse;
import MOAI.moai.common.BaseResponseStatus;
import MOAI.moai.music.dto.CreateMusicDTO;
import MOAI.moai.music.response.MainPageMusicRes;
import MOAI.moai.music.service.MusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MusicController {

    private final MusicService musicService;

    @PostMapping("/music/new")
    public BaseResponse<String> createNewComposerMusic(HttpServletRequest request, CreateMusicDTO dto) throws BaseException {
        musicService.createMusic(request, dto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @GetMapping("/music/{musicId}")
    public BaseResponse<MainPageMusicRes> getMainPageMusicRes(@PathVariable Long musicId, HttpServletRequest request)
            throws BaseException{
        return new BaseResponse<>(musicService.getMainPageMusicRes(request, musicId));
    }
}
