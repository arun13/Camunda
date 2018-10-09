package org.camunda.springboot.send;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.extension.mail.config.MailConfiguration;
import org.camunda.bpm.extension.mail.config.MailConfigurationFactory;
import org.camunda.bpm.extension.mail.notification.MailNotificationService;
import org.apache.commons.mail.HtmlEmail;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class SendTaskEmail implements  TaskListener 
{
	
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub

		
		 // String emailType = (String) delegateTask.getVariable("emailType");
		  //String allowed = (String) execution.getVariable("allowed");
		  
		
		  Map<String, Object> gfg = delegateTask.getVariables();
		  

		  
		  for (Map.Entry<String,Object> entry : gfg.entrySet()) 
	            System.out.println("Key = " + entry.getKey() +
	                             ", Value = " + (String) entry.getValue());
		
		  String emailType = (String) delegateTask.getVariable("emailType");
		  
		  
		  
	      String recipient = "ar.taneja@gmail.com";
	      Properties prop = new Properties();
	  	  InputStream input = null;

	  	try {

	  		input = new FileInputStream("src/main/resources/mail-config.properties");
	  		// load a properties file
	  		prop.load(input);

	  		System.out.println("Task Listener");
	  	
	  		 // Create the email message
	  	  HtmlEmail email = new HtmlEmail();
	  	  email.setHostName(prop.getProperty("mail.smtp.host"));
	  	  email.setSslSmtpPort(prop.getProperty("mail.smtp.port"));
	  	  email.setSSL(Boolean.valueOf(prop.getProperty("mail.smtp.ssl.enable")));
	  	  email.setAuthentication("ar.taneja@gmail.com", "$654321rocky");
	  	  email.addTo(recipient, "Arun Taneja");
	  	  email.setFrom(prop.getProperty("mail.user"), "Me");
	  	  email.setSubject(emailType);
	  	  email.setTextMsg("My mail value ");

	      // send the email
	      email.send();
	  		
	  		
	  	  } 
	  	catch (Exception ex) 
	  	{
	  		ex.printStackTrace();
	  	}
		
		
		
		
	}
}

