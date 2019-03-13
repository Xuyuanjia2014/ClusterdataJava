package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.ArrayList;
import java.util.List;

public class Machine {
    @Id
    private String machineId;

    //private String time_stamp;
    private String disasterLevel1;
    private String disasterLevel2;
    private String cpuNum;
    private String memSize;

    //map in array key is timestamp:

    private List<Double> cpuUsage;
    
    private List<Double> memUsage;

    private List<Double> containerPlan;

    private List<Double> TaskPlan;

    public Machine(){

    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getDisasterLevel1() {
        return disasterLevel1;
    }

    public void setDisasterLevel1(String disasterLevel1) {
        this.disasterLevel1 = disasterLevel1;
    }

    public String getDisasterLevel2() {
        return disasterLevel2;
    }

    public void setDisasterLevel2(String disasterLevel2) {
        this.disasterLevel2 = disasterLevel2;
    }

    public String getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(String cpuNum) {
        this.cpuNum = cpuNum;
    }

    public String getMemSize() {
        return memSize;
    }

    public void setMemSize(String memSize) {
        this.memSize = memSize;
    }

    public List<Double> getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(List<Double> cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public List<Double> getMemUsage() {
        return memUsage;
    }

    public void setMemUsage(List<Double> memUsage) {
        this.memUsage = memUsage;
    }

    public List<Double> getContainerPlan() {
        return containerPlan;
    }

    public void setContainerPlan(List<Double> containerPlan) {
        this.containerPlan = containerPlan;
    }

    public List<Double> getTaskPlan() {
        return TaskPlan;
    }

    public void setTaskPlan(List<Double> taskPlan) {
        TaskPlan = taskPlan;
    }
}
