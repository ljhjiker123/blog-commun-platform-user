package com.jiker.bcp.user.service;

import com.jiker.bcp.user.dto.LoginUserDTO;
import com.jiker.bcp.user.dto.RegisterUserDTO;
import com.jiker.bcp.user.exception.BusinessException;

/**
 * @author jiker.luo
 * @date 2020/4/1
 */
public interface UserService {

    /**
     * 用户注册 Service 层
     * @param registerUserDTO
     * @return
     */
    String register(RegisterUserDTO registerUserDTO) throws BusinessException;

    /**
     * 发送验证码 Servcie 层
     * @param contactPhone
     * @return
     */
    Long sendVerifiCode(Long contactPhone);

    /**
     * 用户登录 Service 层
     * @param loginUserDTO
     * @return
     */
    boolean login(LoginUserDTO loginUserDTO);
}
