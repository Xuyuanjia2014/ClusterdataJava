package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task {
    @Id
    private String taskId;
    @Field
    private int insNum;
    @Field
    private String taskType;
    @Field
    private List<String> statuses;
    @Field
    private Map<String,List<String>> instances;

    public Task(){
        this.statuses = new ArrayList<>();
        this.instances = new HashMap<>();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }

    public Map<String, List<String>> getInstances() {
        return instances;
    }

    public void setInstances(Map<String, List<String>> instances) {
        this.instances = instances;
    }

    public int getInsNum() {
        return insNum;
    }

    public void setInsNum(int insNum) {
        this.insNum = insNum;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}