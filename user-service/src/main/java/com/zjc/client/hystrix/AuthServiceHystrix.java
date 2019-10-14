package com.zjc.client.hystrix;


import com.zjc.client.AuthServiceClient;
import com.zjc.entity.JWT;
import org.springframework.stereotype.Component;

/**
 * 熔断器
 */
@Component
public class AuthServiceHystrix implements AuthServiceClient {

    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        System.out.println("--------获取Token 熔断---------");
        return null;
    }
}
