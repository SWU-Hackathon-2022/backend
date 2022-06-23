package MOAI.moai.login.response;

import MOAI.moai.member.type.MemberType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  로그인 시 넘겨주는 Response 객체
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRes {

    private Long memberId;
    private String nickName;
    private MemberType dtype;
}
