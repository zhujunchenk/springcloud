package com.zjc.rabbit;


import com.alibaba.fastjson.JSON;
import com.zjc.entity.SysLog;
import com.zjc.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * 用于接收RabbitMQ服务器的消息
 */
@Component
public class Receiver {

    //起到了信息量的作用
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    SysLogService sysLogService;

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        SysLog sysLog=  JSON.parseObject(message,SysLog.class);
        //保存日志信息
        sysLogService.saveLogger(sysLog);
        latch.countDown();
    }


}