package com.zjc.controller;


import com.zjc.annotation.SysLogger;
import com.zjc.dto.RespDTO;
import com.zjc.entity.User;
import com.zjc.service.UserService;
import com.zjc.util.BPwdEncoderUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "注册", notes = "username和password为必选项")
    @PostMapping("/registry")
    @SysLogger("registry")
    public User createUser(@RequestBody User user){
        //参数判读省略,判读该用户在数据库是否已经存在省略
        String entryPassword= BPwdEncoderUtils.BCryptPassword(user.getPassword());
        user.setPassword(entryPassword);
        return userService.createUser(user);
    }

    @ApiOperation(value = "登录", notes = "username和password为必选项")
    @PostMapping("/login")
    @SysLogger("login")
    public RespDTO login(@RequestParam String username , @RequestParam String password){
        //参数判读省略
      return  userService.login(username,password);
    }

    @ApiOperation(value = "根据用户名获取用户", notes = "根据用户名获取用户")
    @PostMapping("/{username}")
    @PreAuthorize("hasRole('USER')")
    @SysLogger("getUserInfo")
   // @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public RespDTO getUserInfo(@PathVariable("username") String username){
        //参数判读省略
        User user=  userService.getUserInfo(username);
        return RespDTO.onSuc(user);
    }


}
