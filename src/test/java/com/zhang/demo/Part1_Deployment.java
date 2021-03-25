package com.zhang.demo;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author by SUNS3T
 * @Classname Part1_Deployment
 * @Description 添加资源文件，流程部署，获取部署时间信息等
 */
@SpringBootTest
public class Part1_Deployment {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void initDeploymentBPMN(){
        String filepath = "BPMN/Part1_Deployment.bpmn";
        //String pngpath = "BPMN/BPMN_Deployment.jpg";
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(filepath)
         //       .addClasspathResource(pngpath)//图片
                .name("BPMN流程部署测试")
                .deploy();
        System.out.println(":::::::"+deployment);
    }

    @Test
    public void getDeployment(){
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        for (Deployment deployment : list) {
            System.out.println("id:::"+deployment.getId());
            System.out.println("name:::"+deployment.getName());
            System.out.println("time:::"+deployment.getDeploymentTime());
            System.out.println("key:::"+deployment.getKey());
        }
    }

}
