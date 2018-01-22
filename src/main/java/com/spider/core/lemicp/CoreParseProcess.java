package com.spider.core.lemicp;

import com.spider.bean.Match;
import com.spider.core.ISpider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 2017/10/25.
 */
public class CoreParseProcess {
    private static final Log LOG = LogFactory.getLog(CoreParseProcess.class);
    private static Document doc = null;
    private static ISpider iSpider = new ConcreteSpiderHttp();
    static {
        try {
            doc = Jsoup.parse(iSpider.getHtml());
        } catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
        }
    }

    private static Elements getAllTodayMatchDate(){
        return doc.getElementsByClass("matchdate");
    }

    /**
     * 构造Match
     * @return
     */
    private static Match buildMatch(Element tr) throws Exception{
        try {
            String cid = tr.attr("cid");
            String gameName = tr.attr("gname");
            String gameEndTime = tr.attr("gendtime");
            String homeTeam = tr.attr("hometeam");
            String guestTeam = tr.attr("guestteam");

            if (tr.getElementsByClass("unsaleSpf").size() != 0){//包含未开售，跳过
                return new Match(cid, gameName, gameEndTime, homeTeam, guestTeam, 0, 0, 0, 0, 0, 0, 0);
            }

            Elements pls = tr.getElementsByAttributeValue("name", "pl");
            Double win = Double.valueOf(pls.get(0).text().trim());
            Double drew = Double.valueOf(pls.get(1).text().trim());
            Double defeats = Double.valueOf(pls.get(2).text().trim());
            //让球个数
            Double rqWin = Double.valueOf(pls.get(3).text().trim());
            Double rqDrew = Double.valueOf(pls.get(4).text().trim());
            Double rqDefeats= Double.valueOf(pls.get(5).text().trim());
            String green_newrq ="green newrq";
            String red_newrq = "red newrq";
            Elements rqNumElementsGreen = tr.getElementsByClass(green_newrq);//+
            Elements rqNumElementsRed = tr.getElementsByClass(red_newrq);//-1
            int rqNum = 0;
            if (rqNumElementsRed.size() > 0) {
                rqNum = Integer.valueOf(rqNumElementsRed.text().trim());
            }
            return new Match(cid, gameName, gameEndTime, homeTeam, guestTeam, win, defeats, drew, rqNum, rqWin, rqDefeats, rqDrew);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    private static List<String> getAllDayDivIds(){
        Elements matchdate = getAllTodayMatchDate();
        List<String> dayDivIds = new ArrayList<String>(10);
        for (Element element : matchdate) {
            String text = element.getAllElements().text();
            String date = text.substring(0, 10);
            dayDivIds.add("hist_"+date.replace("-",""));
        }
        return dayDivIds;
    }

    public static List<Match>  getAllMatchDatas() throws Exception{
        List<Match> allMatchData = new ArrayList<Match>(10);
        for (String dayDivId : getAllDayDivIds()) {
            Element dataDiv = doc.getElementById(dayDivId.trim());
            Elements trs = dataDiv.getElementsByTag("tr");
            for(Element tr : trs) {
                allMatchData.add(buildMatch(tr));
            }
            LOG.info("抓取的数据divId:"+dayDivId+",比赛场次:"+trs.size());
        }
        return allMatchData;
    }


    public static void main(String[] args) throws Exception{
        System.out.print(getAllMatchDatas());

    }
}
