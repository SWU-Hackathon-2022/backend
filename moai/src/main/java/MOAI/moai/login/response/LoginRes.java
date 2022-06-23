package MOAI.moai.login.response;

import MOAI.moai.member.type.MemberType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRes {

    private Long memberId;
    private String nickName;
    private MemberType dtype;
}
