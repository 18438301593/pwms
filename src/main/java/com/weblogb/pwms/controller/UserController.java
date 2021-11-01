package com.weblogb.pwms.controller;

import com.weblogb.pwms.config.annotation.Cryptogram;
import com.weblogb.pwms.config.annotation.Decrypt;
import com.weblogb.pwms.config.annotation.Encrypt;
import com.weblogb.pwms.model.User;
import com.weblogb.pwms.model.request.DoRegisterRequest;
import com.weblogb.pwms.model.request.DologinRequest;
import com.weblogb.pwms.model.response.Result;
import com.weblogb.pwms.service.UserService;
import com.weblogb.pwms.util.RsaUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
* @author: Jiajiajia
* @Date: 2021/10/26
* @Description: 管理员登录
*/
@RestController
@RequestMapping("user")
public class UserController implements ApplicationRunner {

    @Resource
    private UserService userService;

    // 是否已经注册超级管理员，首次进入系统需要注册（理论上超管只有一个就是你自己）
    private static boolean alreadyRegister = false;

    /**
     * 跳转登录页面
     * @return
     */
    @GetMapping("login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        if(!alreadyRegister){
            // 没有注册就去注册
            modelAndView.setViewName("register");
        }
        modelAndView.addObject("publicKey", RsaUtil.getPublicKey());
        return modelAndView;
    }

    /**
     * 登录方法，超管忘记密码怎么办？把用户表（user）的用户删了，然后重新启动项目。
     * @param request
     * @return
     */
    @Cryptogram
    @PostMapping("dologin")
    public Result dologin(DologinRequest request, HttpSession session){
        if(!alreadyRegister){
            return Result.ofError("Please register first");
        }
        try {
            this.checkUserNamePassword(request);
            User user = userService.dologin(request);
            session.setAttribute("user",user);
            return Result.of(true);
        } catch (Exception e) {
            return Result.ofException(e);
        }
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("user");
        return new ModelAndView("login");
    }

    /**
     * 注解接口
     * @param request
     * @return
     */
    @Cryptogram
    @PostMapping("register")
    public synchronized Result register(DoRegisterRequest request){
        try {
            this.checkUserNamePassword(request);
            boolean register = userService.register(request);
            if(register){
                alreadyRegister=true;
            }
            return Result.of(register);
        } catch (Exception e) {
            return Result.ofException(e);
        }
    }

    /**
     * 校验参数
     * @param request
     * @throws Exception
     */
    private void checkUserNamePassword(DologinRequest request)throws Exception{
        if(StringUtils.isBlank(request.getUsername())){
            throw new Exception("username error");
        }
        if(request.getUsername().length()<6 ||
            request.getUsername().length()>50){
            throw new Exception("username too length");
        }
        if(StringUtils.isBlank(request.getPassword())){
            throw new Exception("password error");
        }
        if(request.getPassword().length()<60 ||
                request.getPassword().length()>100){
            throw new Exception("password too length");
        }
    }

    /**
     * 项目启动后检查是否有超管
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        alreadyRegister = userService.alreadyRegister();
    }
}
