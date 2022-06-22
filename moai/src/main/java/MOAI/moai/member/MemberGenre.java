package MOAI.moai.member;

import MOAI.moai.member.genre.Genre;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MemberGenre {

    @Id
    @GeneratedValue
    @Column(name = "gen_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbr_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "gen_title")
    private Genre genre;

    private void init(Member member, Genre genre) {
        this.member = member;
        this.genre = genre;
    }

    public static MemberGenre createMemberGenre(Member member, Genre genre) {
        MemberGenre memberGenre = new MemberGenre();
        memberGenre.init(member, genre);
        return memberGenre;
    }
}
