package util;

import Beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.events.Attribute;

public class PermissionUtil {
    public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        if(user==null){
            setRedirectToindex(response);
            return false;
        }
        return true;
    }
    private static void setRedirectToindex(HttpServletResponse response){
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location","/Museum/index");
        return;
    }
}
