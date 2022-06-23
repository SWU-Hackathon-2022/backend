package MOAI.moai.note.repository;

import MOAI.moai.member.Member;
import MOAI.moai.music.Music;
import MOAI.moai.note.Note;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findALlByMusic(Music music);
    List<Note> findAllByMember(Member member);

}
