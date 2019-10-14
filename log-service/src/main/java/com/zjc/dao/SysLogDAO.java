package com.zjc.dao;


import com.zjc.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by fangzhipeng on 2017/7/12.
 */
public interface SysLogDAO extends JpaRepository<SysLog, Long> {
}
