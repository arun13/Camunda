package org.camunda.springboot.listener;

import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProcessMessage  implements JavaDelegate{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessMessage.class);
	

	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info("Message Processed at " + new Date() + " in Scope " + execution.getVariable("number"));
		String number = (String) execution.getVariable("number");
		if(number.equalsIgnoreCase("three"))
		{
			execution.getProcessEngineServices().getRuntimeService().startProcessInstanceByMessage("next");
			
		}
		
		
	}

}
