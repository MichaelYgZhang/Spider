package com.spider.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spider.bean.Match;
import com.spider.service.IMatchService;
import com.spider.utils.ActionResult;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by michael on 2017/11/4.
 */
@RestController
@RequestMapping(value = "/lemicp")
public class LemicpHomeController {
    private static final Log LOG = LogFactory.getLog(LemicpHomeController.class);

    @Value(value = "${lemicp.pageSize}")
    private int pageSize;

    @Autowired
    private IMatchService matchService;
    //index, list ,page
    @RequestMapping(value = "/index/{day}")
    public String index(@PathVariable String day){
        LOG.info("lemicp index today:"+day+",start!"+pageSize);
        List<Match> allMatchs = matchService.getAllMatch();
        return new ActionResult<Match>().setPageSize(pageSize).setResultList(allMatchs).toString();
    }

    @RequestMapping("/list")
    public String getList(Model model){
        model.addAttribute("datas", matchService.getAllMatch());
        return "list";
    }
    //search by gameName or home_team

    @RequestMapping(value = "/search")
    public  String sarch(@PathVariable String param, Model model){
        LOG.info("search param:"+param);
        model.addAttribute("datas", matchService.getAllMatch());
        return "list";
    }
}
