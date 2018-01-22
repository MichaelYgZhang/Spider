package com.spider.web;

import com.spider.bean.Match;
import com.spider.service.IMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@Autowired
	private IMatchService matchService;

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		List<Match> list = matchService.getAllMatch();
		model.put("message", this.message);
		model.put("size", list.size());
		//model.put("datas", list);
		return "welcome";
	}

	@RequestMapping("/list")
	public String getList(Model model){
		model.addAttribute("datas", matchService.getAllMatch());
		return "welcome";
	}

}