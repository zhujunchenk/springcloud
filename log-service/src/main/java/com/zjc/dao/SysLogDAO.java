package com.zjc.dao;


import com.zjc.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SysLogDAO extends JpaRepository<SysLog, Long> {
}
