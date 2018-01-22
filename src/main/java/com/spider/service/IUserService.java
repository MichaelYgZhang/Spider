package com.spider.service;

import com.spider.bean.User;
import org.springframework.stereotype.Service;

/**
 * Created by michael on 2017/10/20.
 */
@Service
public interface IUserService {
    int insert(User user);
    User selectByPrimaryKey(Integer id);
}
