package com.weblogb.pwms.service.impl;

import com.weblogb.pwms.mapper.UserMapper;
import com.weblogb.pwms.model.User;
import com.weblogb.pwms.model.request.DoRegisterRequest;
import com.weblogb.pwms.model.request.DologinRequest;
import com.weblogb.pwms.service.UserService;
import com.weblogb.pwms.util.SHA256;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    /**
     * 检查是否存在超管 true存在，false不存在
     * @return
     */
    @Override
    public boolean alreadyRegister() {
        Integer i = userMapper.alreadyRegister();
        return i==null?false:true;
    }

    /**
     * 注册
     * @param request
     * @return
     */
    @Override
    public boolean register(DoRegisterRequest request) {
        // 校验是否已经注册过
        if(this.alreadyRegister()){
            return false;
        }
        String password = request.getPassword();
        // 再次对密码加密
        request.setPassword(SHA256.getSHA256(password));
        userMapper.register(request);
        return true;
    }

    /**
     * 登录
     * @param request
     * @return
     */
    @Override
    public User dologin(DologinRequest request) {
        User user = userMapper.getUserByUserName(request.getUsername());
        if(user==null){
            // 用户名或密码错误
            throw new RuntimeException("Wrong user name or password");
        }
        String password = request.getPassword();
        // 再次对密码加密
        password = SHA256.getSHA256(password);
        if(user.getPassword()==null || !user.getPassword().equals(password)){
            throw new RuntimeException("Wrong user name or password");
        }
        return user;
    }
}
