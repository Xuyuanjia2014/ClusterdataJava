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
    private Double planCpu;
    @Field
    private Double planMem;

    public Task(){
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public Double getPlanCpu() {
        return planCpu;
    }

    public void setPlanCpu(Double planCpu) {
        this.planCpu = planCpu;
    }

    public Double getPlanMem() {
        return planMem;
    }

    public void setPlanMem(Double planMem) {
        this.planMem = planMem;
    }
}
