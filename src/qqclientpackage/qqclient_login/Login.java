package qqclientpackage.qqclient_login;
/*
 * @author Curry
 * @version 1.0
 *
 * 传输登录信息，持有用户名和密码
 */

public class Login {
    private String userName;
    private String password;

    public Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
