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

@RestController
@RequiredArgsConstructor
@Slf4j
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/note/composer")
    public BaseResponse<String> sendNoteToComposer(@RequestBody SendNoteDTO dto, HttpServletRequest request)
            throws BaseException {
        noteService.sendArtistToComposerNote(request, dto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @PostMapping("/note/artist/accept")
    public BaseResponse<String> sendAcceptNoteToArtist(@RequestBody SendReplyDTO dto, HttpServletRequest request)
            throws BaseException {
        noteService.sendAcceptToArtistNote(request, dto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @PostMapping("/note/artist/decline")
    public BaseResponse<String> sendDeclineNoteToArtist(@RequestBody SendReplyDTO dto, HttpServletRequest request)
            throws BaseException {
        noteService.sendDeclineToArtistNote(request, dto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @GetMapping("/note")
    public BaseResponse<List<NoteListRes>> getAllNotes(HttpServletRequest request) throws BaseException {

        return new BaseResponse<>(noteService.getAllNotesByMember(request));
    }

    @GetMapping("/{noteId}/note")
    public BaseResponse<NoteDetailRes> getOneNoteDetail(@PathVariable Long noteId, HttpServletRequest request)
            throws BaseException {
        return new BaseResponse<>(noteService.getOneNoteDetail(noteId, request));
    }

}
