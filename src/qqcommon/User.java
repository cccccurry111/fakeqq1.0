package qqcommon;
/*
 * @author Curry
 * @version 1.0
 *
 * 表示一个用户信息
 */

import java.io.Serializable;

//用户对象需要在网络上传输，所以需要进行串行化
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userid;
    private String username;
    private String password;
    private String mail;

    public User(){}

    public User(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
