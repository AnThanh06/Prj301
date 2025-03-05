
package Utils;

import javax.servlet.http.HttpSession;


public class AuthUtils {
    public static final String ADMIN_ROLE = "AD";
    public static final String USER_ROLE ="US";
    
    public static boolean isloggedIn(HttpSession session){
        return session.getAttribute("user")!=null;
        
    }
}
    
