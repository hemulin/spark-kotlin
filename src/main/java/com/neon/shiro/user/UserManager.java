
package com.neon.shiro.user;

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
        // Factory<SecurityManager> factory = new IniSecurityManagerFactory("../../src/main/resources/shiro/shiro.ini");
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("/home/hemulin/workspace/work/neontrading/neon-backend-auth/neon-authentication/src/main/resources/shiro.ini");
        securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
    }

    public String login(String email, String password) {

        Subject subject = SecurityUtils.getSubject();
        subject.getSession(true);

        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        token.setRememberMe(true);

        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            Object salt = rng.nextBytes();
            System.out.println("error1");
            return "error1";
        } catch (IncorrectCredentialsException ice) {
            System.out.println("error2");
            return "error2";
        } catch (LockedAccountException lae) {
            System.out.println("error3");
            return "error3";
        } catch (ExcessiveAttemptsException eae) {
            System.out.println("error4");
            return "error4";
        } catch (AuthenticationException ae) {
            //unexpected error?
            System.out.println("error5");
            return "error5";
        }

        // User usr = db.createQuery(User.class).field("alias").equal(user).get();
        // usr.setSubject(subject);

        // subject.getSession().setAttribute("user", usr);

        // return (String) subject.getSession().getId();
        return "";
    }
}