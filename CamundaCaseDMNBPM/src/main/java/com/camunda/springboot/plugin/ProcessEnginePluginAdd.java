package com.camunda.springboot.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandler;
import org.camunda.bpm.spring.boot.starter.configuration.impl.AbstractCamundaConfiguration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.camunda.springboot.duedatelistener.AddWatchDuedateParseListener;
import com.camunda.springboot.duedatelistener.UserTaskOverdueJobHandler;

@Component
@Order(Ordered.LOWEST_PRECEDENCE + 1)
public class ProcessEnginePluginAdd extends AbstractCamundaConfiguration {
	
	private static final Logger log = Logger.getLogger(ProcessEnginePluginAdd.class.getName());
	
	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		if(processEngineConfiguration.getCustomPreBPMNParseListeners() == null) {
			processEngineConfiguration.setCustomPreBPMNParseListeners(new ArrayList<BpmnParseListener>());
		}
		processEngineConfiguration.getCustomPreBPMNParseListeners().add(new AddWatchDuedateParseListener());		

		log.info(this.getClass().getName() + " parse listeners added");
	}
	
	@Override
	public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		// Add Job Handler to execute the Jobs
				if (processEngineConfiguration.getJobHandlers()==null) {
					processEngineConfiguration.setJobHandlers(new HashMap<String, JobHandler>());
				}
				processEngineConfiguration.getJobHandlers().put(UserTaskOverdueJobHandler.USER_TASK_ESCALATION_JOB_HANDLER_TYPE, new UserTaskOverdueJobHandler());
				
				log.info(this.getClass().getName() + " job handler added");
	}
	

}
