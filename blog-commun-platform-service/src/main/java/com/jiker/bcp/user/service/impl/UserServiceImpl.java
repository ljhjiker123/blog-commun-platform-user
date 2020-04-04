package com.jiker.bcp.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.jiker.bcp.user.dao.UserDAO;
import com.jiker.bcp.user.dto.ChangePasswordDTO;
import com.jiker.bcp.user.dto.LoginUserDTO;
import com.jiker.bcp.user.dto.RegisterUserDTO;
import com.jiker.bcp.user.dto.UpdateUserDTO;
import com.jiker.bcp.user.entity.UserInfo;
import com.jiker.bcp.user.exception.BusinessException;
import com.jiker.bcp.user.service.UserService;
import com.jiker.bcp.user.util.RedisUtil;
import com.jiker.bcp.user.vo.QueryUserDetailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiker.bcp.user.util.CacheBeanCopier;

import static com.jiker.bcp.user.enums.BusinessCode.USER_REGISTER_ERROR;
import static com.jiker.bcp.user.enums.BusinessCode.USER_EXISTED;
import static com.jiker.bcp.user.enums.BusinessCode.ACCOUNT_PASSWORD_ERROR;
import static com.jiker.bcp.user.enums.BusinessCode.VERIFICATION_ERROR;
import static com.jiker.bcp.user.enums.BusinessCode.USER_DOESNT_EXIST_ERROR;
import static com.jiker.bcp.user.enums.BusinessCode.NO_PERMISSION_ERROR;
import static com.jiker.bcp.user.enums.BusinessCode.UPDATE_USER_FAILURE;
import static com.jiker.bcp.user.enums.BusinessCode.STORE_VERIFI_CODE_ERROR;
import static com.jiker.bcp.user.enums.VerifyCode.PASSED;
import static com.jiker.bcp.user.enums.ValidityCode.NORMAL;

/**
 * @author jiker.luo
 * @date 2020/4/1
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDao;

    private static final Long TIMEOUT = 300L;

    @Override
    public String register(RegisterUserDTO registerUserDTO) throws BusinessException {
        UserInfo userInfo = new UserInfo();
        CacheBeanCopier.copy(registerUserDTO, userInfo);
        try {
            // 检查用户是否存在
            UserInfo conditon = userDao.selectByCondition(userInfo);
            LOGGER.info("UserServiceImpl register 检查用户是否存在 入参：{} ，返回：{}", userInfo.getContactPhone(), JSON.toJSONString(conditon));
            if (conditon != null) {
                throw new BusinessException(USER_EXISTED);
            }

            int res = userDao.insert(userInfo);
            LOGGER.info("UserServiceImpl register 注册用户 入参：{}", JSON.toJSONString(userInfo));
            if (res == 0) {
                throw new BusinessException(USER_REGISTER_ERROR);
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl register 注册用户失败 入参：{}", JSON.toJSONString(userInfo));
            throw e;
        }
        return userInfo.getId().toString();
    }

    @Override
    public Long sendVerifiCode(Long contactPhone) throws BusinessException {
        String key = contactPhone.toString();
        LOGGER.info("UserServiceImpl sendVerifiCode 发送验证码，入参：{}", contactPhone);
        Long value = randCode();

        // 存入 redis ，设置超时时间 5分钟
        boolean flag = RedisUtil.set(key, value, TIMEOUT);
        LOGGER.info("UserServiceImpl sendVerifiCode 储存到 redis： key:{} value:{}", key, value);
        if (!flag) {
            throw new BusinessException(STORE_VERIFI_CODE_ERROR);
        }
        return value;
    }

    @Override
    public boolean login(LoginUserDTO loginUserDTO) throws BusinessException {
        UserInfo userInfo = new UserInfo();
        CacheBeanCopier.copy(loginUserDTO, userInfo);

        boolean flag = false;
        try {
            // 查看验证码是否正确
            Integer res = (Integer) RedisUtil.get(loginUserDTO.getUsername().toString());
            LOGGER.info("UserServiceImpl login 验证码匹配，返回：{}", RedisUtil.get(loginUserDTO.getUsername().toString()));
            if (res != loginUserDTO.getVerifiCode().intValue()) {
                throw new BusinessException(VERIFICATION_ERROR);
            }

            // 用户登录
            UserInfo condition = userDao.selectByCondition(userInfo);
            LOGGER.info("UserServiceImpl login 用户登录，入参：{}，返回：{}", JSON.toJSONString(userInfo), JSON.toJSONString(condition));
            if (condition == null) {
                throw new BusinessException(ACCOUNT_PASSWORD_ERROR);
            }

            // 判断用户是否审核通过及是否被禁用
            if (!condition.getVerify().equals(PASSED.getCode()) || !condition.getValidity().equals(NORMAL.getCode())) {
                throw new BusinessException(NO_PERMISSION_ERROR);
            }

            flag = true;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl login 用户登录失败，入参：{}", JSON.toJSONString(userInfo));
            throw e;
        }
        return flag;
    }

    @Override
    public QueryUserDetailVO queryUserDetail(Long id) throws BusinessException {
        QueryUserDetailVO queryUserDetailVO = new QueryUserDetailVO();

        try {
            UserInfo userInfo = userDao.selectByPrimaryKey(id);
            LOGGER.info("UserServiceImpl queryUserDetail 查询用户详情，入参：{}，返回：{}", id, JSON.toJSONString(userInfo));
            // 判断传入 id 是否正确
            if (userInfo == null) {
                throw new BusinessException(USER_DOESNT_EXIST_ERROR);
            }

            CacheBeanCopier.copy(userInfo, queryUserDetailVO);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl queryUserDetail 查询用户详情失败，入参：{}", id);
            throw e;
        }

        return queryUserDetailVO;
    }

    @Override
    public Boolean updateUser(UpdateUserDTO updateUserDTO) throws BusinessException {
        boolean flag = false;

        try {
            UserInfo condition = userDao.selectByPrimaryKey(updateUserDTO.getId());
            LOGGER.info("UserServiceImpl updateUser 查询用户详情，入参：{}，返回：{}", updateUserDTO.getId(), JSON.toJSONString(condition));
            // 判断传入 id 是否正确
            if (condition == null) {
                throw new BusinessException(USER_DOESNT_EXIST_ERROR);
            }

            UserInfo userInfo = new UserInfo();
            CacheBeanCopier.copy(updateUserDTO, userInfo);
            userInfo.setUsername(userInfo.getContactPhone());

            // 更新用户信息
            int res = userDao.updateByPrimaryKeySelective(userInfo);
            LOGGER.error("UserServiceImpl updateUser 更新用户信息，入参：{}，返回：{}", JSON.toJSONString(updateUserDTO), res);
            if (res == 0) {
                throw new BusinessException(UPDATE_USER_FAILURE);
            }

            flag = true;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl updateUser 更新用户信息失败，入参：{}", JSON.toJSONString(updateUserDTO));
            throw e;
        }
        return flag;
    }

    @Override
    public Boolean changePasoword(ChangePasswordDTO changePasswordDTO) throws BusinessException {
        boolean flag = false;

        try {
            UserInfo condition = userDao.selectByPrimaryKey(changePasswordDTO.getId());
            LOGGER.info("UserServiceImpl changePasoword 查询用户信息，入参：{}，返回：{}", changePasswordDTO.getId(), JSON.toJSONString(condition));
            if (condition == null) {
                throw new BusinessException(USER_DOESNT_EXIST_ERROR);
            }

            // 判断密码是否正确
            if (!condition.getPassword().equals(changePasswordDTO.getPassword())) {
                throw new BusinessException(ACCOUNT_PASSWORD_ERROR);
            }

            // 修改密码
            UserInfo userInfo = new UserInfo();
            CacheBeanCopier.copy(changePasswordDTO, userInfo);
            userInfo.setPassword(changePasswordDTO.getNewPassword());

            int res = userDao.updateByPrimaryKeySelective(userInfo);
            LOGGER.info("UserServiceImpl changePasoword 修改密码 入参：{}", JSON.toJSONString(userInfo));
            if (res == 0) {
                throw new BusinessException(UPDATE_USER_FAILURE);
            }

            flag = true;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl changePasoword 修改密码失败，入参：{}", JSON.toJSONString(changePasswordDTO));
            throw e;
        }
        return flag;
    }

    /**
     * 随机生成6位数验证码
     *
     * @return
     */
    private Long randCode() {
        long code = (long) (Math.random() * 1000000);
        return code;
    }

}
