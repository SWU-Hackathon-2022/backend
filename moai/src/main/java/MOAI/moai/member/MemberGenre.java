package MOAI.moai.member;

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

    //@Enumerated(EnumType.STRING)
}
