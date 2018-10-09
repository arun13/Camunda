package org.camunda.springboot;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableProcessApplication
@ComponentScan({"com.camunda.springboot.listener"})
public class ProcessStart {
	
	@Autowired
	private RuntimeService runtimeService;
	
	public static void main(String... args) {
		
		SpringApplication.run(ProcessStart.class, args);
		

	}
	
	@EventListener
//	@PostDeploy
	private void processPostDeploy(PostDeployEvent event) {
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("allowed", "noLWP");
		variables.put("ApprovalOutput", "AutoApproved");
		variables.put("approval", "approved");
		variables.put("applicationSufficient", Variables.booleanValue(true));
		variables.put("rating", Variables.integerValue(4));
		
		//runtimeService.startProcessInstanceByMessage("myMessage", variables);
		runtimeService.startProcessInstanceByKey("MyFirstPoolProcessId", variables);

	}
	

}