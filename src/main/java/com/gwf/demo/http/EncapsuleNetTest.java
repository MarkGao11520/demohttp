package com.gwf.demo.http;

import com.alibaba.fastjson.JSON;
import com.gwf.demo.http.dataobject.*;
import com.gwf.demo.http.net.CallbackString;
import com.gwf.demo.http.net.HttpClient;
import com.gwf.demo.http.net.INetResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/8/23.
 * 测试类，调用封装好的异步请求的类要实现INetResult<String>接口
 * 重写起getNetData方法对返回结果进行封装
 */
public class EncapsuleNetTest implements INetResult<String>{
    public static void main(String[] args) {
        HttpClient.createWorkSpace(parseWS(),new CallbackString(new EncapsuleNetTest()));
    }

    /**
     * 模拟获取实体类数据
     * @return
     */
    public static WorkSpace parseWS(){
        WorkSpace workSpace = new WorkSpace();

        workSpace.setName("myworkspace");

        Command command = new Command();
        command.setName("build");
        command.setType("mvn");

        Attributes attributes = new Attributes();
        attributes.setGoal("Build");
        attributes.setPreviewUrl("");

        command.setAttributes(attributes);
        command.setCommandLine("mvn clean install");
        List<Command> commands = new ArrayList<Command>();
        commands.add(command);
        workSpace.setCommands(commands);

        Environments environments = new Environments();

        Recipe recipe = new Recipe();
        recipe.setLocation("eclipse/ubuntu_jdk8");
        recipe.setType("dockerimage");

        Machines machines = new Machines();

        DevMachine devMachine = new DevMachine();
        Attributes attributes1 = new Attributes();
        attributes1.setMemoryLimitBytes("2147483648");
        devMachine.setAttributes(attributes1);
        devMachine.setServers(new Servers());
        String[] l = {"org.eclipse.che.exec","org.eclipse.che.terminal","org.eclipse.che.ws-agent","org.eclipse.che.ssh"};
        devMachine.setAgents(Arrays.asList(l));

        machines.setDevMachine(devMachine);

        Environment environment = new Environment();
        environment.setMachines(machines);
        environment.setRecipe(recipe);

        environments.put("myworkspace",environment);

        workSpace.setEnvironments(environments);
        workSpace.setDefaultEnv("myworkspace");
        workSpace.setProjects(new ArrayList<Project>());
        workSpace.setLinks(new ArrayList<Link>());
        return workSpace;
    }

    public void getNetData(String data) {
        System.out.println(data);
    }
}
