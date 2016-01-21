package com.plum.workflow.component;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.zip.ZipInputStream;

/**
 * Created by Andy on 2016/1/17.
 */
public class DeployComponent {

    private static final Logger logger = LoggerFactory.getLogger(DeployComponent.class);

    private String defaultBarFileName = "path/to/process-one.bar";

    @Autowired
    private RepositoryService repositoryService;

    public void deployProcessDefinitionXml(String processDefinitionXml){

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(processDefinitionXml)
                .deploy();
    }

    public void deployBarFile(String barFileName){

        try {
            if(null == barFileName){
                barFileName = defaultBarFileName;
            }

            ZipInputStream inputStream = new ZipInputStream(new FileInputStream(barFileName));

            /**取出路径最后的名称作为部署名称，这里需要调试是否合理*/
            String deploymentName = barFileName.substring(barFileName.lastIndexOf("/"));

            repositoryService.createDeployment()
                    .name(deploymentName)
                    .addZipInputStream(inputStream)
                    .deploy();

        } catch (FileNotFoundException e) {

            logger.error(barFileName + " not found, please check if path is correct");

            e.printStackTrace();

        }
    }


}
