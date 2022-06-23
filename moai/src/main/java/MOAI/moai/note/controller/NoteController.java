package MOAI.moai.note.controller;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponse;
import MOAI.moai.common.BaseResponseStatus;
import MOAI.moai.note.dto.SendNoteDTO;
import MOAI.moai.note.dto.SendReplyDTO;
import MOAI.moai.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/note/composer")
    public BaseResponse<String> sendNoteToComposer(@RequestBody SendNoteDTO dto, HttpServletRequest request, HttpSession session)
            throws BaseException {
        noteService.sendArtistToComposerNote(request, session, dto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @PostMapping("/note/artist/accept")
    public BaseResponse<String> sendAcceptNoteToArtist(@RequestBody SendReplyDTO dto, HttpServletRequest request, HttpSession session)
            throws BaseException {
        noteService.sendAcceptToArtistNote(request, session, dto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @PostMapping("/note/artist/decline")
    public BaseResponse<String> sendDeclineNoteToArtist(@RequestBody SendReplyDTO dto, HttpServletRequest request, HttpSession session)
            throws BaseException {
        noteService.sendDeclineToArtistNote(request, session, dto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

}
