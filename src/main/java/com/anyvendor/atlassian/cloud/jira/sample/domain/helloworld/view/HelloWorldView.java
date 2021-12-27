package com.anyvendor.atlassian.cloud.jira.sample.domain.helloworld.view;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.atlassian.connect.spring.IgnoreJwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldView {

	@RequestMapping(value = "/hello-world1", method = RequestMethod.GET)
	@ResponseBody
	@IgnoreJwt
	public String helloWorld1(@AuthenticationPrincipal AtlassianHostUser hostUser) {
		return "hello-world";
	}

	@RequestMapping(value = "views/helloworld.html", method = RequestMethod.GET)
	public ModelAndView helloWorld2(@RequestParam String username) {
		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("userName", username);
		return model;
	}

}
