
package com.neon.shiro.user;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class NeonRealm extends AuthorizingRealm {

    protected static final String DEFAULT_AUTHENTICATION_QUERY = "select password from users where username = ?";

    /**
    * The default query used to retrieve account data for the user when {@link #saltStyle} is COLUMN.
    */
    protected static final String DEFAULT_SALTED_AUTHENTICATION_QUERY = "select password, password_salt from users where username = ?";

    /**
    * The default query used to retrieve the roles that apply to a user.
    */
    protected static final String DEFAULT_USER_ROLES_QUERY = "select role_name from user_roles where username = ?";

    /**
    * The default query used to retrieve permissions that apply to a particular role.
    */
    protected static final String DEFAULT_PERMISSIONS_QUERY = "select permission from roles_permissions where role_name = ?";


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
        String username = "liu";
        String salt2 = "0072273a5d87322163795118fdd7c45e";
        String password = "be320beca57748ab9632c4121ccac0db";
        SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo(username, password, getName());
        ai.setCredentialsSalt(ByteSource.Util.bytes(username+salt2));
        return ai;
        // User user = ds.find(User.class).field("_id").equal(at.getPrincipal().toString()).get();
        // if (user != null) {
        //     return new SimpleAuthenticationInfo(user.getAlias(), user.getPasswordHash(),
        //             ByteSource.Util.bytes(user.getPasswordSalt()), this.getName());
        // }
        // throw new AuthenticationException();
    }
}