package MOAI.moai.note.repository;

import MOAI.moai.member.Member;
import MOAI.moai.music.Music;
import MOAI.moai.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByMusic(Music music);
    List<Note> findAllByMember(Member member);
    Optional<Note> findById(Long noteId);

}
