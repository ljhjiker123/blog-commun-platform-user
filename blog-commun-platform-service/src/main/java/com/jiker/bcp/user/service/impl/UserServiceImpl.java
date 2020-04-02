package com.jiker.bcp.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.jiker.bcp.user.dao.UserDAO;
import com.jiker.bcp.user.dto.LoginUserDTO;
import com.jiker.bcp.user.dto.RegisterUserDTO;
import com.jiker.bcp.user.entity.UserInfo;
import com.jiker.bcp.user.exception.BusinessException;
import com.jiker.bcp.user.service.UserService;
import com.jiker.bcp.user.util.RedisUtil;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiker.bcp.user.util.CacheBeanCopier;

import java.util.function.Function;

import static com.jiker.bcp.user.enums.BusinessCode.USER_REGISTER_ERROR;
import static com.jiker.bcp.user.enums.BusinessCode.USER_EXISTED;
import static com.jiker.bcp.user.enums.BusinessCode.ACCOUNT_PASSWORD_ERROR;
import static com.jiker.bcp.user.enums.BusinessCode.VERIFICATION_ERROR;

/**
 * @author jiker.luo
 * @date 2020/4/1
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDao;

    @Override
    public String register(RegisterUserDTO registerUserDTO) throws BusinessException {
        UserInfo userInfo = new UserInfo();
        CacheBeanCopier.copy(registerUserDTO,userInfo);
        try {
            // 检查用户是否存在
            UserInfo conditon = userDao.selectByCondition(userInfo);
            LOGGER.info("UserServiceImpl register 检查用户是否存在 入参：{} ，返回：{}", userInfo.getContactPhone(),JSON.toJSON(conditon));
            if (conditon != null){
                throw new BusinessException(USER_EXISTED);
            }
            userDao.insert(userInfo);
            LOGGER.info("UserServiceImpl register 注册用户 入参：{}", JSON.toJSON(userInfo));
        } catch (BusinessException e){
            throw e;
        }catch (Exception e){
            LOGGER.error("UserServiceImpl register 注册用户失败 入参：{}", JSON.toJSON(userInfo));
            LOGGER.error(e.getMessage(),e);
            throw new BusinessException(USER_REGISTER_ERROR);
        }
        return userInfo.getId().toString();
    }

    @Override
    public Long sendVerifiCode(Long contactPhone) {
        String key = contactPhone.toString();
        LOGGER.info("UserServiceImpl sendVerifiCode 发送验证码，入参：{}",contactPhone);
        Long value = randCode();

        // 存入 redis ，设置超时时间 5分钟
        RedisUtil.set(key,value,300);
        LOGGER.info("UserServiceImpl sendVerifiCode 储存到 redis： key:{} value:",key,value);
        return value;
    }

    @Override
    public boolean login(LoginUserDTO loginUserDTO) throws BusinessException {
        UserInfo userInfo = new UserInfo();
        CacheBeanCopier.copy(loginUserDTO,userInfo);

        boolean flag = false;
        try {
            // 查看验证码是否正确
            Integer res = (Integer) RedisUtil.get(loginUserDTO.getUsername());
            LOGGER.info("UserServiceImpl login 验证码匹配，返回：{}",RedisUtil.get(loginUserDTO.getUsername()));
            if (res != loginUserDTO.getVerifiCode().intValue()){
                throw new BusinessException(VERIFICATION_ERROR);
            }

            // 用户登录
            UserInfo condition = userDao.selectByCondition(userInfo);
            LOGGER.info("UserServiceImpl login 用户登录，入参：{}，返回：{}",JSON.toJSON(userInfo),JSON.toJSON(condition));
            if (condition == null){
                throw new BusinessException(ACCOUNT_PASSWORD_ERROR);
            }

            flag = true;
        } catch (BusinessException e){
            throw e;
        } catch (Exception e){
            LOGGER.error("UserServiceImpl login 用户登录失败，入参：{}",JSON.toJSON(userInfo));
            throw e;
        }
        return flag;
    }

    /**
     * 随机生成6位数验证码
     * @return
     */
    private Long randCode(){
        long code = (long)(Math.random() * 1000000);
        return code;
    }

}
