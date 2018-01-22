package lemicp;

import com.spider.MainApplication;
import com.spider.utils.ActionResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by michael on 2017/11/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
@PropertySource(value = {"classpath:application.properties"}, encoding = "utf-8")
public class PropertiesTest {
    @Value("${lemicp.pageSize}")
    private String pageSize;

    @Test
    public void test_prop(){
        System.out.println("pageSize:"+pageSize);
    }

    @Test
    public void test_actionResult(){
        ActionResult<String> actionResult = new ActionResult<String>();
        System.out.println(actionResult.getPageSize());
    }
}
