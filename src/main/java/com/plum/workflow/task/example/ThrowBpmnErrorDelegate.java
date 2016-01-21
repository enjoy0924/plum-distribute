package com.plum.workflow.task.example;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * Created by Andy on 2016/1/17.
 */
public class ThrowBpmnErrorDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
//        try {
//            executeBusinessLogic();
//        } catch (BusinessException e) {
//            throw new BpmnError("BusinessExceptionOccurred");
//        }
    }
}
