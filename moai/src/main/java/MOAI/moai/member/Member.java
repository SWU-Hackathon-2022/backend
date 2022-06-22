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

    @Column(name = "mbr_login_id")
    private String loginId;

    @Column(name = "mbr_pwd")
    private String password;

    @Column(name = "mbr_name")
    private String name;

    @Column(name = "mbr_nickname")
    private String nickName;

    @Column(name = "mbr_email")
    private String email;


}
