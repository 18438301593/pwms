package com.weblogb.pwms.model.request;

/**
* @author: Jiajiajia
* @Date: 2021/10/27
* @Description: 用户登录请求
*/
public class DologinRequest {

    private String username;
    private String password;

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
}
