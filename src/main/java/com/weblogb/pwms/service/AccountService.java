package com.weblogb.pwms.service;


import com.weblogb.pwms.model.Acc;
import com.weblogb.pwms.model.AccPwd;

import java.util.List;

public interface AccountService {
    /**
     * 账号列表
     * @return
     */
    List<Acc> list();

    /**
     * 添加账号
     * @param accPwd
     * @return
     */
    boolean add(AccPwd accPwd);

    /**
     * 修改账号
     * @param accPwd
     * @return
     */
    boolean update(AccPwd accPwd);

    /**
     * 删除账号
     * @param id
     * @return
     */
    boolean del(Integer id);

    /**
     * 查看密码
     * @param id
     * @return
     */
    String look(Integer id);

    /**
     * 提示
     * @param id
     * @return
     */
    String tips(Integer id);
}
