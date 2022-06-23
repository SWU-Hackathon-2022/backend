package MOAI.moai.note.service;

import MOAI.moai.member.Member;
import MOAI.moai.member.repository.MemberRepository;
import MOAI.moai.member.type.MemberType;
import MOAI.moai.music.Music;
import MOAI.moai.music.repository.MusicRepository;
import MOAI.moai.note.Note;
import MOAI.moai.note.repository.NoteRepository;
import MOAI.moai.note.type.NoteType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteService {

    private final MusicRepository musicRepository;
    private final NoteRepository noteRepository;

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



}
