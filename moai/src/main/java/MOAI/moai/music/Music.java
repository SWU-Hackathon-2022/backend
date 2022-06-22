package MOAI.moai.music;

import MOAI.moai.common.BaseEntity;
import MOAI.moai.member.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Music extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "msc_id")
    private Long musicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msc_comp_id")
    private Member composer;

    @Column(name = "msc_name")
    private String name;

    @Column(name = "msc_file_url")
    private String fileUrl;

    @Column(name = "msc_like_num")
    private Integer likeNum;


}
