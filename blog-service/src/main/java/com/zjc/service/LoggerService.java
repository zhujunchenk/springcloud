/**
 * @program: springcloud
 * @description:
 * @author: zhujunchen
 * @create: 2019-10-13
 **/
package com.zjc.service;

import com.zjc.config.RabbitConfig;
import com.zjc.entity.SysLog;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    //通过AmqpTemplate发送日志代码
    public void log(SysLog sysLog){
        rabbitTemplate.convertAndSend(RabbitConfig.queueName, JSON.toJSONString(sysLog));
    }
}

