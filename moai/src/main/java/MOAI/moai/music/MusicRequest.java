package MOAI.moai.music;

import MOAI.moai.common.BaseEntity;
import MOAI.moai.member.Member;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class MusicRequest extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "req_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msc_id")
    private Music music;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbr_id")
    private Member member;

    @Column(insertable = false, updatable = false)
    private String dtype;

    @Column(name = "req_succ")
    @ColumnDefault("false")
    private Boolean isSuccess;



}
