package com.weblogb.pwms.model;

/**
* @author: Jiajiajia
* @Date: 2021/10/27
* @Description: 账户密码
*/
public class AccPwd extends Acc{
    private String password;
    private String tips;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
