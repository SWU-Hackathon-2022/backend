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
import MOAI.moai.note.response.NoteDetailRes;
import MOAI.moai.note.response.NoteListRes;
import MOAI.moai.note.type.NoteType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  쪽지 관련 Service
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NoteService {

    private final MusicRepository musicRepository;
    private final NoteRepository noteRepository;
    private final MemberRepository memberRepository;
    private final LoginUser loginUser;

    /**
     *
     *  특정 사용자의 전체 쪽지 목록 조회 메서드
     */
    public List<NoteListRes> getAllNotesByMember(HttpServletRequest request) throws BaseException {
        Long loginMemberId = loginUser.getLoginMemberId(request);
        if (loginMemberId == null) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }
        Optional<Member> findMember = memberRepository.findByMemberId(loginMemberId);
        if (findMember.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }
        Member member = findMember.get();

        List<NoteListRes> result = new ArrayList<>();

        // 1차 창작자일 경우
        if (member.getDtype() == MemberType.COMPOSER) {
            List<Music> musicList = musicRepository.findAllByComposer(member);
            for (Music music : musicList) {
                List<Note> noteList = noteRepository.findAllByMusic(music);
                for (Note note : noteList) {
                    if (note.getDtype() == NoteType.COMPOSER) {
                        result.add(new NoteListRes(note.getContent(), note.getCreatedDate()));
                    }
                }
            }
        }
        // 2차 창작자일 경우
        else {
            List<Note> noteList = noteRepository.findAllByMember(member);
            for (Note note : noteList) {
                if (note.getDtype() == NoteType.ARTIST || note.getDtype() == NoteType.NORMAL) {
                    result.add(new NoteListRes(note.getContent(), note.getCreatedDate()));
                }
            }
        }

        return result;

    }

    /**
     *
     *  1개의 쪽지 상세 조회 메서드
     */
    public NoteDetailRes getOneNoteDetail(Long noteId, HttpServletRequest request) throws BaseException {
        // 현재 로그인된 사용자 조회
        Long loginMemberId = loginUser.getLoginMemberId(request);
        if (loginMemberId == null) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }
        Optional<Member> findMember = memberRepository.findByMemberId(loginMemberId);
        if(findMember.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }

        // 자신의 note인지 검증
        Optional<Note> findNote = noteRepository.findById(noteId);
        if (findNote.isEmpty()) {
            throw new BaseException(BaseResponseStatus.REQUEST_ERROR);
        }
        if (findNote.get().getDtype() == NoteType.COMPOSER && findNote.get().getMusic().getComposer().getMemberId() != findMember.get().getMemberId()) {
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }
        else if ((findNote.get().getDtype() == NoteType.ARTIST || findNote.get().getDtype() == NoteType.NORMAL)
                && findNote.get().getMember().getMemberId() != findMember.get().getMemberId()){
            throw new BaseException(BaseResponseStatus.INVALID_USER);
        }

        if (findNote.get().getDtype() == NoteType.COMPOSER || findNote.get().getDtype() == NoteType.ARTIST) {
            return new NoteDetailRes(findNote.get().getMusic().getMusicId(), findNote.get().getId(), findNote.get().getContent(),
                    findNote.get().getCreatedDate(), true);
        }
        else {
            return new NoteDetailRes(findNote.get().getMusic().getMusicId(), findNote.get().getId(), findNote.get().getContent(),
                    findNote.get().getCreatedDate(), false);
        }

    }

    /**
     *
     *  2차 창작자 -> 1차 창작자 쪽지 전송 메서드
     */
    public void sendArtistToComposerNote(HttpServletRequest request, SendNoteDTO dto) throws BaseException {
        Optional<Music> findMusic = musicRepository.findByMusicId(dto.getMusicId());

        // 현재 로그인된 사용자 조회
        Long loginMemberId = loginUser.getLoginMemberId(request);
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

    /**
     *
     *  1차 창작자의 수락 쪽지 전송 메서드
     */
    public void sendAcceptToArtistNote(HttpServletRequest request, SendReplyDTO dto) throws BaseException {
        // 현재 로그인된 사용자 조회
        Long loginMemberId = loginUser.getLoginMemberId(request);
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

    /**
     *
     *  1차 창작자의 거절 쪽지 전송 메서드
     */
    public void sendDeclineToArtistNote(HttpServletRequest request, SendReplyDTO dto) throws BaseException {
        // 현재 로그인된 사용자 조회
        Long loginMemberId = loginUser.getLoginMemberId(request);
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
