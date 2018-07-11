package com.ch3nk.ch3nkSite.modules.test.act;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

public class Act_test_1 {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**创建所需的23张表*/
    @Test
    public void createTables() {
        String resource = "activiti.cfg.xml";
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource(resource)
                .buildProcessEngine();
        System.out.println(processEngine);
    }
    /**部署流程定义*/
    @Test
    public void deployment() {
        /**通过流程图创建流程部署对象*/
        Deployment leaveProcessDeployment = ProcessEngines.getDefaultProcessEngine()    //与流程定义和部署相关的Service
                .getRepositoryService()
                .createDeployment()
                .name("leaveProcessDeployment")
                .addClasspathResource("ch3nkSiteCfgs/activitiBpmns/leave.bpmn")
                .addClasspathResource("ch3nkSiteCfgs/ /leave.png")
                .deploy();
        System.out.println(leaveProcessDeployment.getDeploymentTime());
        System.out.println(leaveProcessDeployment.getId());
        System.out.println(leaveProcessDeployment.getName());

    }
    /**开始一个流程实例*/
    @Test
    public void startProcessInstance() {
        ProcessInstance processInstanceByKey = processEngine.getRuntimeService()        //与正在执行的流程实例和执行对象相关的Service
                .startProcessInstanceByKey("sysProcess_leave");
        System.out.println(processInstanceByKey.getName());
        System.out.println(processInstanceByKey.getDeploymentId());
        System.out.println(processInstanceByKey.getId());       //流程实例id
        System.out.println(processInstanceByKey.getProcessDefinitionId());  //流程定义id

    }
    /**查询当前个人的任务*/
    @Test
    public void queryPersonalTask() {
        List<Task> taskList = processEngine.getTaskService()      //与正在执行的任务管理相关的Service
                .createTaskQuery()  //创建任务查询对象
                .taskAssignee("wangwu")  //个人任务查询方法，指定办理人查询
                .list();
        if (taskList != null) {
            for (Task task : taskList) {
                System.out.println(task.getAssignee());     //办理人
                System.out.println(task.getId());           //任务id
                System.out.println(task.getName());         //任务名称
                System.out.println(task.getCreateTime());         //任务创建时间
                System.out.println(task.getProcessDefinitionId());         //流程定义id
                System.out.println(task.getProcessInstanceId());         //流程实例id
                System.out.println("===============");
            }
        }

    }


    /**办理个人任务*/
    @Test
    public void doMyTask() {
        processEngine.getTaskService()
                .complete("12502");
    }
}
