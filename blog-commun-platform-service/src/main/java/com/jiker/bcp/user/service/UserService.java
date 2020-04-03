package com.jiker.bcp.user.service;

import com.jiker.bcp.user.dto.ChangePasswordDTO;
import com.jiker.bcp.user.dto.LoginUserDTO;
import com.jiker.bcp.user.dto.RegisterUserDTO;
import com.jiker.bcp.user.dto.UpdateUserDTO;
import com.jiker.bcp.user.exception.BusinessException;
import com.jiker.bcp.user.vo.QueryUserDetailVO;

/**
 * @author jiker.luo
 * @date 2020/4/1
 */
public interface UserService {

    /**
     * 用户注册 Service 层
     *
     * @param registerUserDTO
     * @return
     */
    String register(RegisterUserDTO registerUserDTO) throws BusinessException;

    /**
     * 发送验证码 Servcie 层
     *
     * @param contactPhone
     * @return
     */
    Long sendVerifiCode(Long contactPhone);

    /**
     * 用户登录 Service 层
     *
     * @param loginUserDTO
     * @return
     */
    boolean login(LoginUserDTO loginUserDTO);

    /**
     * 查询用户详情 Service 层
     *
     * @param id
     * @return
     */
    QueryUserDetailVO queryUserDetail(Long id);

    /**
     * 更新用户 Service 层
     *
     * @param updateUserDTO
     * @return
     */
    Boolean updateUser(UpdateUserDTO updateUserDTO);

    /**
     * 修改密码 Service 层
     *
     * @param changePasswordDTO
     * @return
     */
    Boolean changePasoword(ChangePasswordDTO changePasswordDTO);
}
