package com.zhang.demo;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author by SUNS3T
 * @Classname Part2_ProcessDefinition
 * @Description 获取版本号、key、资源名称、部署ID
 */
@SpringBootTest
public class Part2_ProcessDefinition {

    @Autowired
    private RepositoryService repositoryService;

    //查询流程定义
    public void getProcessDefinition(){
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
    }
    //删除流程定义
    public void delProcessDefinition(){
        String pdID="";
        repositoryService.deleteDeployment(pdID, false);
    }
}
