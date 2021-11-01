package com.weblogb.pwms.controller;

import com.weblogb.pwms.config.annotation.Decrypt;
import com.weblogb.pwms.config.annotation.Encrypt;
import com.weblogb.pwms.model.AccPwd;
import com.weblogb.pwms.model.response.Result;
import com.weblogb.pwms.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;

/**
* @author: Jiajiajia
* @Date: 2021/10/28
* @Description: 账号管理
*/
@RestController
@RequestMapping("account")
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 列表页
     * @return
     */
    @GetMapping("index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    /**
     * 获取列表
     * @return
     */
    @Encrypt
    @PostMapping("list")
    public Result list(){
        return Result.of(accountService.list());
    }

    /**
     * 录入
     * @return
     */
    @Encrypt
    @Decrypt
    @PostMapping("add")
    public Result add(AccPwd accPwd){
        if(StringUtils.isBlank(accPwd.getPlatform())
                ||StringUtils.isBlank(accPwd.getAccount())
                ||StringUtils.isBlank(accPwd.getPassword())){
            return Result.ofError("请完善信息");
        }
        if(accPwd.getPlatform().length()>100
                ||accPwd.getAccount().length()>100
                ||accPwd.getPassword().length()>30
                ||(StringUtils.isNotBlank(accPwd.getTips()) && accPwd.getTips().length()>100)){
            return Result.ofError("字段过长");
        }
        if(accountService.add(accPwd)){
            return Result.of(accPwd.getId());
        }
        return Result.ofError("添加失败");
    }

    /**
     * 录入
     * @return
     */
    @Encrypt
    @Decrypt
    @PostMapping("update")
    public Result update(AccPwd accPwd){
        if(accPwd.getId()==null
                ||StringUtils.isBlank(accPwd.getPlatform())
                ||StringUtils.isBlank(accPwd.getAccount())){
            return Result.ofError("请完善信息");
        }
        if(accPwd.getPlatform().length()>100
                ||accPwd.getAccount().length()>100
                ||(StringUtils.isNotBlank(accPwd.getPassword()) && accPwd.getPassword().length()>30)
                ||(StringUtils.isNotBlank(accPwd.getTips()) && accPwd.getTips().length()>100)){
            return Result.ofError("字段过长");
        }
        return Result.of(accountService.update(accPwd));
    }

    /**
     * 删除
     * @return
     */
    @Encrypt
    @Decrypt
    @PostMapping("del")
    public Result del(AccPwd accPwd){
        if(accPwd.getId() == null){
            return Result.ofError("参数异常");
        }
        return Result.of(accountService.del(accPwd.getId()));
    }

    /**
     * 查看密码
     * @return
     */
    @Encrypt
    @Decrypt
    @PostMapping("look")
    public Result look(AccPwd accPwd){
        if(accPwd.getId() == null){
            return Result.ofError("参数异常");
        }
        return Result.of(accountService.look(accPwd.getId()));
    }

    /**
     * 查看提示
     * @return
     */
    @Encrypt
    @Decrypt
    @PostMapping("tips")
    public Result tips(AccPwd accPwd){
        if(accPwd.getId() == null){
            return Result.ofError("参数异常");
        }
        return Result.of(accountService.tips(accPwd.getId()));
    }
}
