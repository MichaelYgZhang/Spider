package web;

import java.util.Date;

import com.spider.Dao.Mapper.IUserDao;
import com.spider.MainApplication;
import com.spider.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SpringBootDemo281ApplicationTests {

	@Autowired
	private IUserDao mapper;

	@Test
	public void insert() {
		User roncooUser = new User();
		roncooUser.setName("测试abcdefet");
		roncooUser.setCreateTime(new Date());
		int result = mapper.insert(roncooUser);
		System.out.println(result);
	}

}
