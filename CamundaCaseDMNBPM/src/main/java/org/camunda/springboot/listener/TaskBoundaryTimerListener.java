package org.camunda.springboot.listener;

import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A lister that will be invoked on DemoTask boundary events.
 */
public class TaskBoundaryTimerListener implements ExecutionListener {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(TaskBoundaryTimerListener.class);

    @Override
    public void notify(DelegateExecution execution) throws Exception {

        LOGGER.info("Timer fired at " + new Date() + " in Scope " + execution.getId());

        // fetch the BPMN model ID of the timer event
        String timerEventId = execution.getCurrentActivityId();

        // get the task this timer event is attached to
        Job timerJob = execution.getProcessEngineServices().getManagementService().createJobQuery()
                .activityId(timerEventId).singleResult();
        Task task = execution.getProcessEngineServices().getTaskService().createTaskQuery()
                .executionId(timerJob.getExecutionId()).singleResult();

        if (task.getDueDate() != null && task.getDueDate().before(new Date())) {
            LOGGER.info("the due date (" + task.getDueDate()
                    + ") of the User Task was reached");
            execution.getProcessEngineServices().getRuntimeService().setVariableLocal(
                    task.getExecutionId(), "taskBoundaryEvent.dueDateReached", true);
        }

        // set a trigger marker variable on the local task scope
        execution.getProcessEngineServices().getRuntimeService()
                .setVariableLocal(task.getExecutionId(), "taskBoundaryEvent.fired", true);

    }
}