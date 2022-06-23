package MOAI.moai.member;

import MOAI.moai.common.BaseEntity;
import MOAI.moai.member.type.MemberType;
import lombok.Getter;

import javax.persistence.*;

/**
 *  Member Entity
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "mbr_id")
    private Long memberId;

    @Enumerated(EnumType.STRING)
    private MemberType dtype;

    @Column(name = "mbr_token")
    private String token;

    @Column(name = "mbr_prof_url")
    private String memberThumbnailUrl;

    @Column(name = "mbr_nickname")
    private String nickName;



    private void init (MemberType dtype, String token, String memberThumbnailUrl,
                   String nickName) {
        this.dtype = dtype;
        this.token = token;
        this.memberThumbnailUrl = memberThumbnailUrl;
        this.nickName = nickName;
    }

    /**
     *
     *  Member 생성 메서드
     */

    public static Member createMember(MemberType dtype, String token, String memberThumbnailUrl,
                                      String nickName) {
        Member member = new Member();
        member.init(dtype, token, memberThumbnailUrl, nickName);
        return member;
    }

}
