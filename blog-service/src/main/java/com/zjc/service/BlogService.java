/**
 * @program: springcloud
 * @description:
 * @author: zhujunchen
 * @create: 2019-10-13
 **/
package com.zjc.service;

import com.zjc.client.UserServiceClient;
import com.zjc.dao.BlogDao;
import com.zjc.dto.BlogDetailDTO;
import com.zjc.dto.RespDTO;
import com.zjc.entity.Blog;

import com.zjc.entity.User;
import com.zjc.exception.CommonException;
import com.zjc.exception.ErrorCode;
import com.zjc.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;



@Service
public class BlogService {
    @Autowired
    BlogDao blogDao;

    @Autowired
    UserServiceClient userServiceClient;

    /**
     * 添加博客
     * @param blog
     * @return
     */
    public Blog postBlog(Blog blog){
        return blogDao.save(blog);
    }

    /**
     * 根据用户名查找博客
     * @param username
     * @return
     */
    public List<Blog> findBlogs(String username){
        return blogDao.findByUsername(username);
    }

    /**
     * 查看博客详情
     * @param id
     * @return 返回博客和用户信息
     */
    public BlogDetailDTO findBlogDetail(Long id){
        //Optional类用来解决空指针异常问题
        Optional<Blog> blogOptional=blogDao.findById(id);
        Blog blog=null;
        //判断如果blogOptional不为空,则取回实际值对象
        if (blogOptional!=null){
            //从 Optional 实例中取回实际值对象的方法之一是使用 get() 方法：
            blog=blogOptional.get();
        }
        //如果对象为空,则抛出异常,博客不存在
        if (null==blog){
            throw new CommonException(ErrorCode.BLOG_IS_NOT_EXIST);
        }
        //获取当前用户Token以及用户名,如果为空则抛异常
        RespDTO<User> respDTO=userServiceClient.getUser(UserUtils.getCurrentToken(),blog.getUsername());
        if (respDTO==null){
            throw new CommonException(ErrorCode.RPC_ERROR);
        }
        //把博客和用户信息赋值给blogDetailDTO对象
        BlogDetailDTO blogDetailDTO=new BlogDetailDTO();
        blogDetailDTO.setBlog(blog);
        blogDetailDTO.setUser(respDTO.data);
        return blogDetailDTO;

    }
}

