package com.spider.job;

import com.alibaba.fastjson.JSONObject;
import com.spider.bean.Match;
import com.spider.core.lemicp.CoreParseProcess;
import com.spider.service.IMatchService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by michael on 2017/10/25.
 */
@Component
public class SpiderScheduledJob {

    private static final Log LOG = LogFactory.getLog(SpiderScheduledJob.class);

    private static final ThreadLocal<DateFormat> sdf = new ThreadLocal<DateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    @Autowired
    private IMatchService matchService;

//    @Scheduled(cron="0 30 6-22 * * ?"
    @Scheduled(cron="0 0/59 * * * ?")
    public void executeSpiderTask() {
        long start = System.currentTimeMillis();
        LOG.info("start-ime:"+sdf.get().format(new Date()));
        execute();
        LOG.info("end-ime:"+sdf.get().format(new Date())+",耗时:"+(System.currentTimeMillis()-start));


    }

    private void execute(){
        int newMatchCount = 0, plChangedCount = 0;//一种是新增比赛，一种是赔率发生变化的比赛
        try {
            List<Match> matches = CoreParseProcess.getAllMatchDatas();
            for (Match match : matches) {
                Match dbMatch = matchService.getMatch(match);
                if (match.equals(dbMatch)) continue;
                if (null != dbMatch) ++plChangedCount;
                else ++newMatchCount;
                if (!matchService.store(match)) {
                    LOG.error("数据入库异常:" + JSONObject.toJSONString(match));
                }
            }
            LOG.info("时间:"+sdf.get().format(new Date())+";" +
                    "爬出比赛场次共:"+matches.size()+"场"
                    + ",赔率变化场次:"+plChangedCount
                    + ",新增比赛场次:"+newMatchCount
                    + ",赔率未发生变化场次:"+(matches.size()-plChangedCount-newMatchCount));
        } catch (Exception ex) {
            ex.printStackTrace();
            LOG.error(ex.getMessage(), ex);
        }
    }

    //TODO IP proxy池子
    //TODO 完赛情况爬虫以及报表，完赛1：赔率变化N
}
