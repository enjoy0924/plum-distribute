package com.plum.workflow.service;

import org.activiti.engine.task.Task;

import java.util.List;

/**
 * Created by Andy on 2016/1/17.
 */
public interface BPMService {

    void startProcess(String processKey);

    List<Task> getTasksByAssignee(String assignee);

    List<Task> getTasksByCandidateGroup(String group);

    List<Task> getAllTasks(String assignee);

    void claimTask(String taskId, String assignee);

    void completeTask(String taskId);

    void getProcHistoryByProcId(String procId);
}
