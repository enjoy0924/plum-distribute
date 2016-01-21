package com.plum.workflow.task.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * Created by Andy on 2016/1/17.
 */
public class ReverseStringsFieldDelegate implements JavaDelegate {

    private Expression text1;
    private Expression text2;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String value1 = (String) text1.getValue(execution);
        execution.setVariable("var1", new StringBuffer(value1).reverse().toString());

        String value2 = (String) text2.getValue(execution);
        execution.setVariable("var2", new StringBuffer(value2).reverse().toString());

    }
}
