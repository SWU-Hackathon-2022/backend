package MOAI.moai.note.service;

import MOAI.moai.common.BaseException;
import MOAI.moai.common.BaseResponseStatus;
import MOAI.moai.login.LoginUser;
import MOAI.moai.member.Member;
import MOAI.moai.member.repository.MemberRepository;
import MOAI.moai.member.type.MemberType;
import MOAI.moai.music.Music;
import MOAI.moai.music.repository.MusicRepository;
import MOAI.moai.note.Note;
import MOAI.moai.note.dto.SendNoteDTO;
import MOAI.moai.note.dto.SendReplyDTO;
import MOAI.moai.note.repository.NoteRepository;
import MOAI.moai.note.type.NoteType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteService {

    private final MusicRepository musicRepository;
    private final NoteRepository noteRepository;
    private final MemberRepository memberRepository;

    public List<Note> getAllNotesByMember(Member member) {
        List<Note> result = new ArrayList<>();

        if (member.getDtype() == MemberType.COMPOSER) {
            List<Music> musicList = musicRepository.findAllByComposer(member);
            for (Music music : musicList) {
                List<Note> noteList = noteRepository.findALlByMusic(music);
                for (Note note : noteList) {
                    if (note.getDtype() == NoteType.COMPOSER) {
                        result.add(note);
                    }
                }
            }
        }
        else {
            List<Note> noteList = noteRepository.findAllByMember(member);
            for (Note note : noteList) {
                if (note.getDtype() == NoteType.ARTIST || note.getDtype() == NoteType.NORMAL) {
                    result.add(note);
                }
            }
        }

        return result;

    }

    public void sendArtistToComposerNote(HttpServletRequest request, HttpSession session, SendNoteDTO dto) throws BaseException {
        Optional<Music> findMusic = musicRepository.findByMusicId(dto.getMusicId());

        // 현재 로그인된 사용자 조회
        Long loginMemberId = LoginUser.getLoginMemberId(request, session);
        if (loginMemberId == null) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }
        Optional<Member> findMember = memberRepository.findByMemberId(loginMemberId);
        if(findMember.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }

        if (findMusic.isEmpty()) {
            throw new BaseException(BaseResponseStatus.REQUEST_ERROR);
        }
        Note note = Note.createArtistToComposerNote(findMusic.get(), findMember.get(), dto.getContent());
        noteRepository.save(note);

    }

    public void sendAcceptToArtistNote(HttpServletRequest request, HttpSession session, SendReplyDTO dto) throws BaseException {
        // 현재 로그인된 사용자 조회
        Long loginMemberId = LoginUser.getLoginMemberId(request, session);
        if (loginMemberId == null) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }
        Optional<Member> findMember = memberRepository.findByMemberId(loginMemberId);
        if(findMember.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }

        Optional<Note> findNote = noteRepository.findById(dto.getNoteId());

        if (findNote.isEmpty()) {
            throw new BaseException(BaseResponseStatus.REQUEST_ERROR);
        }

        Note note = Note.createComposerToArtistAcceptNote(findNote.get().getMusic(), findNote.get().getMember(), findNote.get());
        noteRepository.save(note);
    }

    public void sendDeclineToArtistNote(HttpServletRequest request, HttpSession session, SendReplyDTO dto) throws BaseException {
        // 현재 로그인된 사용자 조회
        Long loginMemberId = LoginUser.getLoginMemberId(request, session);
        if (loginMemberId == null) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }
        Optional<Member> findMember = memberRepository.findByMemberId(loginMemberId);
        if(findMember.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }

        Optional<Note> findNote = noteRepository.findById(dto.getNoteId());

        if (findNote.isEmpty()) {
            throw new BaseException(BaseResponseStatus.REQUEST_ERROR);
        }

        Note note = Note.createComposerToArtistDeclineNote(findNote.get().getMusic(), findNote.get().getMember(), findNote.get());
        noteRepository.save(note);
    }



}
