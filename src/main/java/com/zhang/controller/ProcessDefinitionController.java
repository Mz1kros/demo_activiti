package com.zhang.controller;

import com.zhang.util.GlobalConfig;
import com.zhang.util.ResultJson;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * @author by SUNS3T
 * @Classname ProcessDefinitionController
 * @Description TODO
 */
@RestController
@RequestMapping("/processDefinition")
public class ProcessDefinitionController {

    @Autowired
    private RepositoryService repositoryService;

    //添加流程定义 上传BPMN
    @PostMapping("/uploadStreamAndDeployment")
    public ResultJson uploadStreamAndDeployment(@RequestParam("processFile") MultipartFile multipartFile, @RequestParam("deploymentName") String deploymentName) {
        // 获取上传的文件名
        String fileName = multipartFile.getOriginalFilename();
        try {
            String extension = FilenameUtils.getExtension(fileName);
            // 得到输入流（字节流）对象
            InputStream inputStream = multipartFile.getInputStream();
            Deployment deployment = null;
            if (extension.equals("zip")) {
                ZipInputStream zipIn = new ZipInputStream(inputStream);
                deployment = repositoryService.createDeployment().addZipInputStream(zipIn)
                        .name(deploymentName)
                        .deploy();
            } else {
                deployment = repositoryService.createDeployment().addInputStream(fileName, inputStream)
                        .name(deploymentName)
                        .deploy();
            }
            return ResultJson.ResData(GlobalConfig.ResponseCode.SUCCESS.getCode(),
                    GlobalConfig.ResponseCode.SUCCESS.getDesc(), deployment.getId() + ";" + fileName);
        } catch (Exception e) {
            return ResultJson.ResData(GlobalConfig.ResponseCode.ERROR.getCode(),
                    "部署流程失败", e.toString());
        }
    }

    //添加流程定义 在线提交BPMNXML
    @PostMapping("/addDeploymentByString")
    public ResultJson addDeploymentByString(@RequestParam("stringBPMN") String stringBPMN, @RequestParam("deploymentName") String deploymentName) {
        try {
            Deployment deployment = repositoryService.createDeployment()
                    .addString("CreateWithBPMNJS.bpmn", stringBPMN)
                    .name("不知道在哪显示的部署名称")
                    .deploy();
            return ResultJson.ResData(GlobalConfig.ResponseCode.SUCCESS.getCode(),
                    GlobalConfig.ResponseCode.SUCCESS.getDesc(), deployment.getId());
        } catch (Exception e) {
            return ResultJson.ResData(GlobalConfig.ResponseCode.ERROR.getCode(),
                    "string部署流程失败", e.toString());
        }
    }

    //获取流程定义列表
    @GetMapping("/getDefinitions")
    public ResultJson getProcessDefinition() {
        try {
            List<HashMap<String, Object>> listmap = new ArrayList<>();
            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
            for (ProcessDefinition pd : list) {
                HashMap<String, Object> map = new HashMap<>();
                //System.out.println("流程定义ID："+pd.getId());
                map.put("processDefinitionID", pd.getId());
                map.put("name", pd.getName());
                map.put("key", pd.getKey());
                map.put("resourceName", pd.getResourceName());
                map.put("deploymentID", pd.getDeploymentId());
                map.put("version", pd.getVersion());
                listmap.add(map);
            }
            return ResultJson.ResData(GlobalConfig.ResponseCode.SUCCESS.getCode(),
                    GlobalConfig.ResponseCode.SUCCESS.getDesc(), listmap);
        } catch (Exception e) {
            return ResultJson.ResData(GlobalConfig.ResponseCode.ERROR.getCode(),
                    "获取流程定义失败", e.toString());
        }
    }

    //获取流程定义XML
    @GetMapping("/getDefinitionXML")
    public void getDefinitionXML(HttpServletResponse response,
                                 @RequestParam("deploymentId") String deploymentId,
                                 @RequestParam("resourceName") String resourceName) {
        try {
            InputStream inputStream = repositoryService.getResourceAsStream(deploymentId, resourceName);
            int count = inputStream.available();
            byte[] bytes = new byte[count];
            response.setContentType("text/html");
            OutputStream outputStream = response.getOutputStream();
            while (inputStream.read(bytes) != -1) {
                outputStream.write(bytes);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.toString();
        }
    }

    //获取流程部署列表
    @GetMapping("/getDeployments")
    public ResultJson getDeployments() {
        try {
            List<HashMap<String, Object>> listmap = new ArrayList<>();
            List<Deployment> list = repositoryService.createDeploymentQuery().list();
            for (Deployment dep : list) {
                HashMap<String, Object> map = new HashMap<>();
                //System.out.println("流程定义ID："+pd.getId());
                map.put("ID", dep.getId());
                map.put("Name", dep.getName());
                map.put("DeploymentTime", dep.getDeploymentTime());
                listmap.add(map);
            }
            return ResultJson.ResData(GlobalConfig.ResponseCode.SUCCESS.getCode(),
                    GlobalConfig.ResponseCode.SUCCESS.getDesc(), listmap);
        } catch (Exception e) {
            return ResultJson.ResData(GlobalConfig.ResponseCode.ERROR.getCode(),
                    "获取流程部署失败", e.toString());
        }
    }

    //删除流程定义
    @GetMapping("/delDefinition")
    public ResultJson delDefinition(@RequestParam("pdID") String pdID) {
        try {
            repositoryService.deleteDeployment(pdID, true);
            return ResultJson.ResData(GlobalConfig.ResponseCode.SUCCESS.getCode(),
                    GlobalConfig.ResponseCode.SUCCESS.getDesc(), null);
        } catch (Exception e) {
            return ResultJson.ResData(GlobalConfig.ResponseCode.ERROR.getCode(),
                    "删除流程失败", e.toString());
        }
    }
}
