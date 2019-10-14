package com.zjc.service;


import com.zjc.client.AuthServiceClient;
import com.zjc.dao.UserDao;
import com.zjc.dto.LoginDTO;
import com.zjc.dto.RespDTO;
import com.zjc.entity.JWT;
import com.zjc.entity.User;
import com.zjc.exception.CommonException;
import com.zjc.exception.ErrorCode;
import com.zjc.util.BPwdEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    AuthServiceClient authServiceClient;

    public User createUser(User user){
      return  userDao.save(user);
    }

    public User getUserInfo(String username){
        return userDao.findByUsername(username);
    }

    public RespDTO login(String username , String password){
       User user= userDao.findByUsername(username);
       if(null==user){
           throw new CommonException(ErrorCode.USER_NOT_FOUND);
       }
       if(!BPwdEncoderUtils.matches(password,user.getPassword())){
           throw new CommonException(ErrorCode.USER_PASSWORD_ERROR);
       }

        JWT jwt = authServiceClient.getToken("Basic dWFhLXNlcnZpY2U6MTIzNDU2", "password", username, password);
        // 获得用户菜单
        if(null==jwt){
            throw new CommonException(ErrorCode.GET_TOKEN_FAIL);
        }

        LoginDTO loginDTO=new LoginDTO();
        loginDTO.setUser(user);
        loginDTO.setToken(jwt.getAccess_token());
        return RespDTO.onSuc(loginDTO);
    }
}
