package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

public class Container {
    @Id
    private String containerId;
    @Field
    private String appDu;
    @Field
    private List<CStatus> statuses;
    @Field
    private List<CUsage> usages;

    public Container() {
        this.statuses = new ArrayList<>();
        this.usages = new ArrayList<>();
    }

    public class CStatus {
        @Field
        private int timestamp;
        @Field
        private String status;

        @Field
        private String plansR;

        @Field
        private String machineId;

        public CStatus(){

        }

        public CStatus(int timestamp, String status, String plansR, String machineId) {
            this.timestamp = timestamp;
            this.status = status;
            this.plansR = plansR;
            this.machineId = machineId;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPlansR() {
            return plansR;
        }

        public void setPlansR(String plansR) {
            this.plansR = plansR;
        }

        public String getMachineId() {
            return machineId;
        }

        public void setMachineId(String machineId) {
            this.machineId = machineId;
        }
    }

    public class CUsage {
        @Field
        private int timestamp;

        @Field
        private String machineId;

        @Field
        private String actualsR;

        public CUsage(){

        }

        public CUsage(int timestamp, String machineId, String actualsR) {
            this.timestamp = timestamp;
            this.machineId = machineId;
            this.actualsR = actualsR;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getMachineId() {
            return machineId;
        }

        public void setMachineId(String machineId) {
            this.machineId = machineId;
        }

        public String getActualsR() {
            return actualsR;
        }

        public void setActualsR(String actualsR) {
            this.actualsR = actualsR;
        }
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getAppDu() {
        return appDu;
    }

    public void setAppDu(String appDu) {
        this.appDu = appDu;
    }

    public List<CStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<CStatus> statuses) {
        this.statuses = statuses;
    }

    public List<CUsage> getUsages() {
        return usages;
    }

    public void setUsages(List<CUsage> usages) {
        this.usages = usages;
    }
}
