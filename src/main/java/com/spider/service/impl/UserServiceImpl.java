package com.spider.service.impl;

import com.spider.Dao.Mapper.IUserDao;
import com.spider.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by michael on 2017/10/20.
 */
@Service
public class UserServiceImpl implements IUserDao {
    @Autowired
    private IUserDao mapper;

    @Override
    public int insert(User record) {
        User roncooUser = new User();
        roncooUser.setName("测试controller");
        roncooUser.setCreateTime(new Date());
        int result = mapper.insert(roncooUser);
        System.out.print("====="+result);
        return result;
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }
}
