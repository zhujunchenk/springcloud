package com.zjc.client;



import com.zjc.client.hystrix.AuthServiceHystrix;
import com.zjc.entity.JWT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;




@FeignClient(value = "uaa-service",fallback = AuthServiceHystrix.class )
public interface AuthServiceClient {

    /**
     * 获取JWT
     * @param authorization
     * @param type
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/oauth/token")
    JWT getToken(@RequestHeader(value = "Authorization") String authorization,
                 @RequestParam("grant_type") String type,
                 @RequestParam("username") String username,
                 @RequestParam("password") String password);
}



