package MOAI.moai.common.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUser {

    public static Long getLoginMemberId(HttpServletRequest request, HttpSession session) {
        try {
            for (Cookie cookie : request.getCookies()) {
                if (((String)cookie.getName()).equals("id")) {
                    return (Long) session.getAttribute(((String)cookie.getName()));
                }
            }
            return null;
        }
        catch (Exception e) {
            return null;
        }

    }
}
