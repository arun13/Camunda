package com.camunda.springboot.duedatelistener;

import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandler;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandlerConfiguration;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.impl.persistence.entity.TaskEntity;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.task.Task;

public class UserTaskOverdueJobHandler implements JobHandler {
	
	private static final Logger log = Logger.getLogger(UserTaskOverdueJobHandler.class.getName());

	public static final String USER_TASK_ESCALATION_JOB_HANDLER_TYPE = "userTaskOverdueJob";

	@Override
	public String getType() {
		return USER_TASK_ESCALATION_JOB_HANDLER_TYPE;
	}

	@Override
	public void execute(JobHandlerConfiguration jobHandlerConfig, ExecutionEntity execution, CommandContext commandContext, String configuration) {
		// TODO Auto-generated method stub
		log.info("\n\nEscalation received! " + execution.getId() + "\n\n");
		execution.setVariable("approval", "escalated");
		RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
	    List<TaskEntity> taskEntity = execution.getTasks();
	    System.out.println(taskEntity.size());
	    TaskEntity taskEnt = taskEntity.get(0);
	    taskEnt.complete();
		//TaskService taskService = execution.getProcessEngineServices().getTaskService();
		//Task task = taskService.createTaskQuery().executionId(execution.getActivityInstanceId()).singleResult();
		//System.out.println(task);
		//System.out.println(task.getExecutionId());
		//System.out.println(task.getId());
	    /*
		runtimeService.createProcessInstanceModification(execution.getProcessInstanceId())
	    (execution.getActivityInstanceId())
	    .execute();
	    */
	}

	@Override
	public JobHandlerConfiguration newConfiguration(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDelete(JobHandlerConfiguration arg0, JobEntity arg1) {
		// TODO Auto-generated method stub
		
	}

}