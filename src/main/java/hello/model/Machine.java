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

    private List<StatusInt> statuses;
    
    private List<UsageInt> usages;

    public Machine(){
        this.statuses = new ArrayList<>();
        this.usages = new ArrayList<>();
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

    public List<StatusInt> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<StatusInt> statuses) {
        this.statuses = statuses;
    }

    public List<UsageInt> getUsages() {
        return usages;
    }

    public void setUsages(List<UsageInt> usages) {
        this.usages = usages;
    }
}
