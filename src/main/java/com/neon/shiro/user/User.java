
package com.neon.shiro.user;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

public class User {

    private String passwordHash = "";
    private byte[] passwordSalt;

    private String email = "";

    private String verificationId;
    private boolean activated = false;

    private Subject subject;

    public User() {
        //Empty constructor
    }

    public User(String email, String password, ByteSource salt) {
        setEmail(email);

        //Now hash the plain-text password with the random salt and multiple
        //iterations and then Base64-encode the value (requires less space than Hex):
        int iterations = 5000;
        String b64 = new Sha256Hash(password, salt, iterations).toBase64();
        setPasswordHash(b64);
        setPasswordSalt(salt.getBytes());
    }

    public User(Subject sub) {
        setSubject(sub);
    }

    public boolean isLoggedIn() {
        if (getSubject() != null) {
            if (getSubject().isAuthenticated()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * @param passwordHash the passwordHash to set
     */
    public final void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * @return the passwordSalt
     */
    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    /**
     * @param passwordSalt the passwordSalt to set
     */
    public final void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public final void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public final void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * @return the verificationId
     */
    public String getVerificationId() {
        return verificationId;
    }

    /**
     * @param verificationId the verificationId to set
     */
    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

    /**
     * @return the activated
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * @param activated the activated to set
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

}