package MOAI.moai.note.controller;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponse;
import MOAI.moai.common.BaseResponseStatus;
import MOAI.moai.login.LoginUser;
import MOAI.moai.note.dto.SendNoteDTO;
import MOAI.moai.note.dto.SendReplyDTO;
import MOAI.moai.note.response.NoteDetailRes;
import MOAI.moai.note.response.NoteListRes;
import MOAI.moai.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *  Note(쪽지) 관련 Controller
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class NoteController {

    private final NoteService noteService;

    /**
     *
     *  2차 창착자 -> 1차 창작자 쪽지 전송
     */
    @PostMapping("/note/composer")
    public BaseResponse<String> sendNoteToComposer(@RequestBody SendNoteDTO dto, HttpServletRequest request)
            throws BaseException {
        noteService.sendArtistToComposerNote(request, dto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    /**
     *
     *  1차 창작자의 수락 쪽지 전송
     */
    @PostMapping("/note/artist/accept")
    public BaseResponse<String> sendAcceptNoteToArtist(@RequestBody SendReplyDTO dto, HttpServletRequest request)
            throws BaseException {
        noteService.sendAcceptToArtistNote(request, dto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    /**
     *
     *  1차 창작자의 거절 쪽지 전송
     */
    @PostMapping("/note/artist/decline")
    public BaseResponse<String> sendDeclineNoteToArtist(@RequestBody SendReplyDTO dto, HttpServletRequest request)
            throws BaseException {
        noteService.sendDeclineToArtistNote(request, dto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    /**
     *
     *  특정 사용자의 모든 쪽지 list 반환 API
     */
    @GetMapping("/note")
    public BaseResponse<List<NoteListRes>> getAllNotes(HttpServletRequest request) throws BaseException {

        return new BaseResponse<>(noteService.getAllNotesByMember(request));
    }

    /**
     *
     *  특정 쪽지 상세 API
     */
    @GetMapping("/{noteId}/note")
    public BaseResponse<NoteDetailRes> getOneNoteDetail(@PathVariable Long noteId, HttpServletRequest request)
            throws BaseException {
        return new BaseResponse<>(noteService.getOneNoteDetail(noteId, request));
    }

}
