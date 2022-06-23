package MOAI.moai.creation;

import MOAI.moai.common.BaseEntity;
import MOAI.moai.member.Member;
import MOAI.moai.music.Music;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Creation extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ctn_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "msc_id")
    private Music music;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbr_id")
    private Member artist;

    @Column(name = "ctn_name")
    private String name;

    @Column(name = "ctn_file_url")
    private String fileUrl;

    @Column(name = "ctn_like_num")
    private Integer likeNum;

    private void init(Music music, Member artist, String name, String fileUrl){
        this.music = music;
        this.artist = artist;
        this.name = name;
        this.fileUrl = fileUrl;
        this.likeNum = 0;
    }

    public static Creation createCreation(Music music, Member artist, String name, String fileUrl) {
        Creation creation = new Creation();
        creation.init(music, artist, name, fileUrl);

        return creation;
    }
}
