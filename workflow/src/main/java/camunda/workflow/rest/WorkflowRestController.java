package camunda.workflow.rest;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/start")
public class WorkflowRestController {
	
	@Autowired
	  private ProcessEngine camunda;
	
	@RequestMapping(method=RequestMethod.POST)
	  public void startProcessPOST() {
		startProcess();
	  }

	  /**
	   * we need a method returning the {@link ProcessInstance} to allow for easier tests,
	   * that's why I separated the REST method (without return) from the actual implementaion (with return value)
	   */
	  public ProcessInstance startProcess() {
		  
	    return camunda.getRuntimeService().startProcessInstanceByKey("workflow","variables");
	  }
	

}
