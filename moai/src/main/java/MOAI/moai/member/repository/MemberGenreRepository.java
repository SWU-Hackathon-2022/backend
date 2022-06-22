package MOAI.moai.member.repository;

import MOAI.moai.member.Member;
import MOAI.moai.member.MemberGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberGenreRepository extends JpaRepository<MemberGenre, Long> {

    List<MemberGenre> findAllByMember(Member member);

}
