package com.weblogb.pwms.mapper;

import com.weblogb.pwms.model.User;
import com.weblogb.pwms.model.request.DoRegisterRequest;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    Integer alreadyRegister();

    void register(@Param("request") DoRegisterRequest request);

    User getUserByUserName(@Param("username")String username);
}
