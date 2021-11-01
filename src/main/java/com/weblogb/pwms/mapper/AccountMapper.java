package com.weblogb.pwms.mapper;


import com.weblogb.pwms.model.Acc;
import com.weblogb.pwms.model.AccPwd;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AccountMapper {
    List<Acc> list();

    void add(@Param("acc") AccPwd accPwd);

    void update(@Param("acc")AccPwd accPwd);

    boolean del(@Param("id")Integer id);

    String look(@Param("id")Integer id);

    String tips(@Param("id")Integer id);
}
