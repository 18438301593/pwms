package com.weblogb.pwms.service.impl;

import com.weblogb.pwms.mapper.AccountMapper;
import com.weblogb.pwms.model.Acc;
import com.weblogb.pwms.model.AccPwd;
import com.weblogb.pwms.service.AccountService;
import com.weblogb.pwms.util.AesUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author: Jiajiajia
* @Date: 2021/10/28
* @Description: 账号管理
*/
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    private static final String key="com.weblogb.pwms";

    @Override
    public List<Acc> list() {
        return accountMapper.list();
    }

    /**
     * 密码使用aes算法加密
     * @param accPwd
     * @return
     */
    private boolean encryptPassword(AccPwd accPwd){
        String encrypt=null;
        try {
            encrypt = AesUtil.encrypt(accPwd.getPassword(), key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        accPwd.setPassword(encrypt);
        return true;
    }

    @Override
    public boolean add(AccPwd accPwd) {
        if(!encryptPassword(accPwd)){
            return false;
        }
        accountMapper.add(accPwd);
        return true;
    }

    @Override
    public boolean update(AccPwd accPwd) {
        if(accPwd.getPassword()!=null && !encryptPassword(accPwd)){
            return false;
        }
        accountMapper.update(accPwd);
        return true;
    }

    @Override
    public boolean del(Integer id) {
        return accountMapper.del(id);
    }

    @Override
    public String look(Integer id) {
        String look = accountMapper.look(id);
        try {
            return AesUtil.decrypt(look, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "密码解析异常";
    }

    @Override
    public String tips(Integer id) {
        return accountMapper.tips(id);
    }
}
