package org.camunda.springboot.listener;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.Variables;

@ProcessApplication("Loan Approval App CMMN")
public class CreateCase implements ExecutionListener {



@Override
public void notify(DelegateExecution execution) throws Exception {
	
	
	CaseService caseService = execution.getProcessEngineServices().getCaseService();
    caseService.createCaseInstanceByKey("LeaveApprovalCase",
        Variables.createVariables()
          .putValue("applicationSufficient", Variables.booleanValue(true))
          .putValue("rating", Variables.integerValue(4)));

	
}
}