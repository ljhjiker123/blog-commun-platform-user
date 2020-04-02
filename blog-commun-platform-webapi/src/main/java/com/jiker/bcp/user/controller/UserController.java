package com.jiker.bcp.user.controller;

import com.jiker.bcp.user.dto.LoginUserDTO;
import com.jiker.bcp.user.dto.RegisterUserDTO;
import com.jiker.bcp.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.jiker.bcp.user.validator.ValidatorGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiker.luo
 * @date 2020/4/1
 */
@Api(value = "/user",description = "用户操作相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "测试接口",notes = "测试 swagger 接口",httpMethod = "GET",response = HashMap.class)
    @RequestMapping(value = "/test/{id}",method = RequestMethod.GET,produces = "application/json")
    public Object test(@ApiParam(required = true,value = "id 不能为空") @PathVariable Long id){
        Map<String,Object> map = new HashMap<>();
        map.put("res",id);
        return map;
    }

    @ApiOperation(value = "注册用户接口",notes = "注册用户",httpMethod = "POST",response = String.class)
    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = "application/json")
    public Object register(@RequestBody @Validated(ValidatorGroup.Insert.class)
                                   RegisterUserDTO registerUserDTO, BindingResult result){
        return userService.register(registerUserDTO);
    }

    @ApiOperation(value = "发送短信验证码接口",notes = "发送短信验证码",httpMethod = "GET",response = Long.class)
    @RequestMapping(value = "/sendVerifiCode/{contactPhone}",method = RequestMethod.GET,produces = "application/json")
    public Object sendVerifiCode(@ApiParam(required = true,value = "联系电话不能为空") @PathVariable Long contactPhone){
        return userService.sendVerifiCode(contactPhone);
    }

    @ApiOperation(value = "用户登录接口",notes = "用户登录",httpMethod = "POST",response = Boolean.class)
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json")
    public Object login(@RequestBody @Validated(ValidatorGroup.Insert.class)
                                LoginUserDTO loginUserDTO, BindingResult result){
        return userService.login(loginUserDTO);
    }

}
