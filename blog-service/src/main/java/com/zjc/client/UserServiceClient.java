/**
 * @program: springcloud
 * @description:
 * @author: zhujunchen
 * @create: 2019-10-13
 **/
package com.zjc.client;

import com.zjc.client.hystrix.UserServiceHystrix;
import com.zjc.dto.RespDTO;
import com.zjc.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(value = "user-service",fallback = UserServiceHystrix.class)
public interface UserServiceClient {

    @PostMapping(value = "/user/{username}")
    RespDTO<User> getUser(@RequestHeader(value = "Authorization")String token,
                          @PathVariable("username") String username);
}

