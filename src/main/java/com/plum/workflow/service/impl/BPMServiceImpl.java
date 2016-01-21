package com.plum.workflow.service.impl;

import com.plum.cas.service.UserService;
import com.plum.workflow.service.BPMService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/1/17.
 */
@Service
public class BPMServiceImpl implements BPMService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Transactional
    public void startProcess(String processKey){
        runtimeService.startProcessInstanceByKey(processKey);
    }

    @Override
    public List<Task> getTasksByAssignee(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    @Override
    public List<Task> getTasksByCandidateGroup(String group) {

        return taskService.createTaskQuery().taskCandidateGroup(group).list();
    }

    @Transactional
    public List<Task> getAllTasks(String assignee){
        List<Task> tasks = new ArrayList<>();

        /*
        * 这里可能需要加上获取某个任务分配者所在的群组信息，然后获取这个群组里面的所有任务信息
        */
        tasks.addAll(getTasksByAssignee(assignee));

        return tasks;
    }

    @Override
    public void claimTask(String taskId, String assignee) {

        taskService.claim(taskId, assignee);
    }

    @Override
    public void completeTask(String taskId) {

        taskService.complete(taskId);
    }

    @Override
    public void getProcHistoryByProcId(String procId) {
        // verify that the process is actually finished

        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult();
        System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
    }


}
