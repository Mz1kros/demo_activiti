package com.zhang.demo;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author by SUNS3T
 * @Classname Part3_ProcessInstance
 * @Description 流程实例
 */
@SpringBootTest
public class Part3_ProcessInstance {

    @Autowired
    private RuntimeService runtimeService;
    //初始化
    @Test
    public void initProcessInstance(){
        //1、获取页面表单填报的内容，请假时间，请假事由，String formData
        //2、fromData 写入业务表，返回业务表主键ID==businessKey
        //3、把业务数据与Activiti7流程数据关联
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1","bKey002");
        System.out.println("流程实例ID："+processInstance.getProcessDefinitionId());
    }
    //获取
    @Test
    public void getProcessInstances(){
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
        for(ProcessInstance pi : list){
            System.out.println("--------流程实例------");
            System.out.println("ProcessInstanceId："+pi.getProcessInstanceId());
            System.out.println("ProcessDefinitionId："+pi.getProcessDefinitionId());
            System.out.println("isEnded"+pi.isEnded());
            System.out.println("isSuspended："+pi.isSuspended());
        }
    }
    //暂停与激活
    @Test
    public void activeProcessInstance(){
        // runtimeService.suspendProcessInstanceById("73f0fb9a-ce5b-11ea-bf67-dcfb4875e032");
        //System.out.println("挂起流程实例");

        runtimeService.activateProcessInstanceById("73f0fb9a-ce5b-11ea-bf67-dcfb4875e032");
        System.out.println("激活流程实例");
    }
    //删除
    @Test
    public void delProcessInstance(){
        runtimeService.deleteProcessInstance("73f0fb9a-ce5b-11ea-bf67-dcfb4875e032","删着玩");
        System.out.println("删除流程实例");
    }
}