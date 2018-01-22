package com.spider.core.lemicp;

import com.spider.core.ISpider;
import com.spider.utils.AbstractHttpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpRequestBase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConcreteSpiderHttp extends AbstractHttpUtil implements ISpider{
	private static final Log LOG = LogFactory.getLog(ConcreteSpiderHttp.class.getSimpleName());
	public static final String DEFAULT_PATTERN = "yyyyMMddHHmmssSSS";
	private static final ThreadLocal<DateFormat> THREADLOCAL = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(DEFAULT_PATTERN);
		};
	};

	static String lvtValue = "c0666189eef2c2319a97be854f739519";
	static String klbrsidValue = "1509494931";

	@Override
	protected void buildHeader(HttpRequestBase httpRequestBase, String cookie) {
		setHeader(httpRequestBase, cookie);
	}

	private static void setHeader(HttpRequestBase httpRequestBase, String cookie){
		httpRequestBase.setHeader("Accept","text/html,application/xhtml+xml,application/xml,text/plain;charset=utf-8;q=0.9,image/webp,image/apng,*/*;q=0.8");
		httpRequestBase.setHeader("Content-Type", "application/json;charset=utf-8");
		httpRequestBase.setHeader("Accept-Encoding", "gzip,deflate"); //乱码处理重点 http://blog.csdn.net/zzq900503/article/details/39203331
		httpRequestBase.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpRequestBase.setHeader("Cache-Control", "max-age=0");
		httpRequestBase.setHeader("Connection", "keep-alive");
		httpRequestBase.setHeader("Cookie", cookie);
		httpRequestBase.setHeader("Host", "8.lemicp.com");
		httpRequestBase.setHeader("Referer", "http://8.lemicp.com/trade");
		httpRequestBase.setHeader("Upgrade-Insecure-Requests", "1");
		httpRequestBase.setHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
		httpRequestBase.setHeader("x-forwarded-for","");//TODO 从池子里面随机拿出来
	}

	public static String buildCookie(String timeValue, String klbrsidValue){
		//cookie生成规则
		long now = System.currentTimeMillis();
		long lpvtTime = now/1000;
		long klbrsidTime = (now+1000)/1000;
		String cookie = "JSESSIONID=3B1CED2C9CFAEA12B1DF9075AFA9BBE3-n1; " +
				"tag_from=9000; pro_from=100; " +
				"Hm_lvt_6afd594b9573ae83f4da48297d09bb17=1508419951,1508853257,1508872943,1509026279; " +
				"Hm_lpvt_6afd594b9573ae83f4da48297d09bb17="+lpvtTime+
				"; KLBRSID="+klbrsidValue+"|"+klbrsidTime+ //变化上一个加1
 				"|"+timeValue;
		return cookie;
	}

	public static String getKlbrsidValue(String setCookie){
		return setCookie.substring(8, 40);
	}

	public static String getTimeValue(String setCookie){
		return setCookie.substring(52, 62);
	}

	@Override
	public String getHtml()throws Exception{
		try {
			String url = "http://8.lemicp.com/trade/jczq/hhtz";//?playid=9006&e=2
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("playid", 9006);
			params.put("e", 2);
			String setCookieValue = executeHttpGetCookie(url,params, buildCookie(lvtValue, klbrsidValue));
			lvtValue = getTimeValue(setCookieValue);
			klbrsidValue = getKlbrsidValue(setCookieValue);
			return executeHttpGet(url, params, buildCookie(lvtValue, klbrsidValue));
		} catch (Exception e) {
			throw e;
		}
	}

	//TODO cookie 变化的 注意修改
	public static void main(String[] args) throws IOException {
		ConcreteSpiderHttp util = new ConcreteSpiderHttp();
		String url = "http://8.lemicp.com/trade/jczq/hhtz";//?playid=9006&e=2
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playid", 9006);
		params.put("e", 2);
//		String lvtValue = "bedea8ce0f983584f316a2516a052acb";
//		String klbrsidValue = "1509025183";
		String setCookieValue = util.executeHttpGetCookie(url,params, buildCookie(lvtValue, klbrsidValue));
		System.out.println(setCookieValue);
		String result = util.executeHttpGet(url, params,
				buildCookie(getTimeValue(setCookieValue),
							getKlbrsidValue(setCookieValue)));
		Document document = Jsoup.parse(result);
		Elements elements = document.getElementsByClass("matchdate");
		for (Element element : elements) {
			System.out.println(element.text());
		}
	}
}
