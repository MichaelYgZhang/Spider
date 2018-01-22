package com.spider.web;

import com.spider.service.IUserService;
import com.spider.service.impl.UserServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by michael on 2017/10/20.
 */
@RestController
@RequestMapping(value = "/home")
public class HomeController {

    private static final Log LOG = LogFactory.getLog("homeControllerLogger");
//    protected css Logger logger=LoggerFactory.getLogger(HomeController.class);

    @Value(value = "${spider.secret}")
    private String secret;

    @Value(value = "${lemicp.desc}")
    private String desc;

    //yaml file
    @Value(value = "${spring.profiles.active}")
    private String active;


    @Autowired
    private UserServiceImpl userService;

    @RequestMapping
    public String home(){
        LOG.info("infoLog");
        LOG.debug("debugLog");
        LOG.error("errorLog");
        return "Hello " + desc+",active:"+active;
    }

    @RequestMapping(value = "/secret")
    public String secret(){
        return secret;
    }

    @RequestMapping(value = "/get/{id}/{name}")
    public String idName(@PathVariable int id, @PathVariable String name){
        return "url,id:"+id+",name:"+name;
    }

    @RequestMapping(value = "insert-and-get/{id}")
    public String insertAndGet(@PathVariable int id){
        userService.insert(null);
        return userService.selectByPrimaryKey(id).getName();
    }
}
