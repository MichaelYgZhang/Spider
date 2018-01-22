package lemicp;

import com.alibaba.fastjson.JSONObject;
import com.spider.Dao.Mapper.IMatchDao;
import com.spider.MainApplication;
import com.spider.bean.Match;
import com.spider.core.lemicp.CoreParseProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by michael on 2017/10/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class LemiCpTest {

    @Autowired
    private IMatchDao iMatch;

    @Test
    public void store(){
        try {
           // System.out.println("cleanAll:"+iMatch.cleanAll());
            List<Match> matches = CoreParseProcess.getAllMatchDatas();
            int insertCount = 0;
            for (Match match : matches) {
                Match dbMatch = iMatch.getMatch(match);
                if (match.equals(dbMatch)) continue;
                System.out.println("newMatch==="+JSONObject.toJSONString(match));
                System.out.println("dbMatch==="+JSONObject.toJSONString(dbMatch));
                int result = iMatch.store(match);
                if (result <= 0){
                    System.out.printf("error:"+ JSONObject.toJSONString(match));
                }
                ++insertCount;
            }
            System.out.println("变化赔率个数:"+insertCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void matchDataIsChanged(){
        Match match = new Match();
        match.setCid("4776787");
        System.out.println(iMatch.getMatch(match));
    }

    @Test
    public void getAllMatch(){
        List<Match> allMatchs = iMatch.getAllMatch();
        for (Match m : allMatchs) {
            System.out.println(m.toString());
        }
    }
}
