package com.anyvendor.atlassian.cloud.jira.sample.domain.helloworld.rest;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.atlassian.connect.spring.AtlassianHostRestClients;
import com.atlassian.connect.spring.AtlassianHostUser;
import com.atlassian.connect.spring.ContextJwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class HelloWorldRest {

	@Autowired
	private AtlassianHostRestClients atlassianHostRestClients;

	@GetMapping
	@RequestMapping("/api/issue/{issueId}")
	@ContextJwt
	public ResponseEntity<String> helloWorld(@AuthenticationPrincipal AtlassianHostUser hostUser, @PathVariable("issueId") String issueId) {
		final Object forObject = atlassianHostRestClients.authenticatedAsAddon().getForObject("/rest/api/3/issue/" + issueId, Object.class);
		final ObjectMapper objectMapper = new ObjectMapper();
		Writer jsonWriter = new StringWriter();
		try {
			objectMapper.writeValue(jsonWriter, forObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok("Hello " + hostUser.getHost() + ". You are in context endpoint. Here is your issue " + issueId + ": " + jsonWriter);
	}



}
