
package backend.user;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class UserManager {

    // private Datastore db;

    RandomNumberGenerator rng = new SecureRandomNumberGenerator();

    private final SecurityManager securityManager;

    public UserManager() {
        // db = null;
        //load security
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("./src/main/resources/shiro/shiro.ini");
        securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
    }

    public String login(String user, String password) {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession(true);

        UsernamePasswordToken token = new UsernamePasswordToken(user, password);
        token.setRememberMe(true);

        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {

            return "";
        } catch (IncorrectCredentialsException ice) {

            return "";
        } catch (LockedAccountException lae) {

            return "";
        } catch (ExcessiveAttemptsException eae) {

            return "";
        } catch (AuthenticationException ae) {
            //unexpected error?

            return "";
        }

        // User usr = db.createQuery(User.class).field("alias").equal(user).get();
        // usr.setSubject(subject);

        // subject.getSession().setAttribute("user", usr);

        // return (String) subject.getSession().getId();
        return "";
    }
}