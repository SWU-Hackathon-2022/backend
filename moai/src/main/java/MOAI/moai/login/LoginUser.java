package MOAI.moai.login;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginUser {

    public static Long getLoginMemberId(HttpServletRequest request, HttpSession session) {
        try {
            for (Cookie cookie : request.getCookies()) {
                if (((String)cookie.getName()).equals("id")) {
                    return (Long) session.getAttribute(((String)cookie.getValue()));
                }
            }
            return null;
        }
        catch (Exception e) {
            return null;
        }

    }

}
