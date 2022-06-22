package MOAI.moai.common.login;

import MOAI.moai.member.Member;
import MOAI.moai.member.repository.MemberRepository;

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
