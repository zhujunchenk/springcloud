package com.zjc.service;


import com.zjc.dao.SysLogDAO;
import com.zjc.entity.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysLogService {

    @Autowired
    SysLogDAO sysLogDAO;

    public void saveLogger(SysLog sysLog){
        sysLogDAO.save(sysLog);
    }
}
