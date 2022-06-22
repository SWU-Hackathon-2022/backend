package MOAI.moai.music;

import MOAI.moai.member.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Music {

    @Id
    @GeneratedValue
    @Column(name = "msc_id")
    private Long musicId;

    @OneToOne
    @JoinColumn(name = "mbr_id")
    private Member member;


}
