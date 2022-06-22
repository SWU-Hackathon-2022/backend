package MOAI.moai.member;

import MOAI.moai.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "mbr_id")
    private Long memberId;

    @Column(insertable = false, updatable = false)
    private String dtype;

    @Column(name = "mbr_prof_img_url")
    private String profileImgUrl;

    @Column(name = "mbr_login_id")
    private String loginId;

    @Column(name = "mbr_pwd")
    private String password;

    @Column(name = "mbr_name")
    private String name;

    @Column(name = "mbr_nickname")
    private String nickName;

    private void init (String dtype, String profileImgUrl, String loginId, String password, String name,
                   String nickName) {
        this.dtype = dtype;
        this.profileImgUrl = profileImgUrl;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
    }

    public static Member createMember(String dtype, String profileImgUrl, String loginId, String password, String name,
                                      String nickName) {
        Member member = new Member();
        member.init(dtype, profileImgUrl, loginId, password, name, nickName);
        return member;
    }

}
