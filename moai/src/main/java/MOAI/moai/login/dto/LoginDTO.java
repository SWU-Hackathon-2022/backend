package MOAI.moai.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  Login 시 넘겨받는 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private String token;
    private String imageUrl;
    private String email;
    private String expire;
}
