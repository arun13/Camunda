package com.workflow.features;

import java.util.logging.Logger;
//import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
//import org.camunda.bpm.engine.variable.value.FileValue;

public class FeatureHello implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(FeatureHello.class.getName());

    public FeatureHello() {
    }

    public void execute(DelegateExecution execution) throws Exception 
    
    {
      System.out.println("Approved"); 
    
    }
}
