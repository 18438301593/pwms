package com.weblogb.pwms.service;

import com.weblogb.pwms.model.User;
import com.weblogb.pwms.model.request.DoRegisterRequest;
import com.weblogb.pwms.model.request.DologinRequest;

/**
* @author: Jiajiajia
* @Date: 2021/10/28
* @Description: 用户管理
*/
public interface UserService{

    /**
     * 检查是否存在超管
     * @return
     */
    boolean alreadyRegister();

    /**
     * 注册
     * @param request
     * @return
     */
    boolean register(DoRegisterRequest request);

    /**
     * 登录
     * @param request
     * @return
     */
    User dologin(DologinRequest request);
}