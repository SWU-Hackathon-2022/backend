package MOAI.moai.music.repository;

import MOAI.moai.member.Member;
import MOAI.moai.music.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {

    List<Music> findAllByComposer(Member member);

}
