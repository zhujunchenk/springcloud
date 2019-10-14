package com.zjc.dao;


import com.zjc.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface BlogDao extends JpaRepository<Blog, Long> {

    List<Blog> findByUsername(String username);

}
