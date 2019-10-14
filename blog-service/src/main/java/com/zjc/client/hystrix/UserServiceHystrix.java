package com.zjc.client.hystrix;


import com.zjc.client.UserServiceClient;
import com.zjc.dto.RespDTO;
import com.zjc.entity.User;
import org.springframework.stereotype.Component;



@Component
public class UserServiceHystrix implements UserServiceClient {

    @Override
    public RespDTO<User> getUser(String token, String username) {
        System.out.println(token);
        System.out.println(username);
        return null;
    }
}
