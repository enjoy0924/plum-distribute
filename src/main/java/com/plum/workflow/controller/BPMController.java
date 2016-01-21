package com.plum.workflow.controller;

import com.plum.core.dto.CommonResultDTO;
import com.plum.workflow.dto.TaskRepresentation;
import com.plum.workflow.service.BPMService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/1/17.
 */
@Controller
@RequestMapping("bpm")
public class BPMController {

    @Autowired
    private BPMService bpmService;

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 发起一个业务流程
     *
     * @param processKey
     * @return
     */
    @RequestMapping(value="/process", method= RequestMethod.POST)
    @ResponseBody
    public CommonResultDTO startProcessInstance(String processKey) {
        bpmService.startProcess(processKey);

        CommonResultDTO commonResultDTO = new CommonResultDTO();

        return commonResultDTO;
    }


    /**
     * 根据用户的ID，获取用户的所有任务
     *
     * @param assignee
     * @return
     */
    @RequestMapping(value="/tasks", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResultDTO getTasks(String assignee) {
        List<Task> tasks = bpmService.getTasksByAssignee(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }

        CommonResultDTO commonResultDTO = new CommonResultDTO();
        commonResultDTO.setAttach(dtos);

        return commonResultDTO;
    }

    @RequestMapping("image")
    public InputStream getProcessImage(String processDefKey) {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefKey)
                .singleResult();

        String diagramResourceName = processDefinition.getDiagramResourceName();
        InputStream imageStream = repositoryService.getResourceAsStream(
                processDefinition.getDeploymentId(), diagramResourceName);

        return imageStream;
    }
}
