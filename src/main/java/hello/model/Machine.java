package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection="machine")
public class Machine {
    @Id
    @Field("machine_id")
    private String machine_id;

    //private String time_stamp;
    private String disaster_level_1;
    private String disaster_level_2;
    private String cpu_num;
    private String mem_size;

    //map in array key is timestamp:
    @Field("statuses")
    private List<StatusInt> statuses;
    @Field("usages")
    private List<UsageInt> usages;

    public Machine(){
        this.statuses = new ArrayList<>();
        this.usages = new ArrayList<>();
    }

    public String getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(String machine_id) {
        this.machine_id = machine_id;
    }

    public String getDisaster_level_1() {
        return disaster_level_1;
    }

    public void setDisaster_level_1(String disaster_level_1) {
        this.disaster_level_1 = disaster_level_1;
    }

    public String getDisaster_level_2() {
        return disaster_level_2;
    }

    public void setDisaster_level_2(String disaster_level_2) {
        this.disaster_level_2 = disaster_level_2;
    }

    public String getCpu_num() {
        return cpu_num;
    }

    public void setCpu_num(String cpu_num) {
        this.cpu_num = cpu_num;
    }

    public String getMem_size() {
        return mem_size;
    }

    public void setMem_size(String mem_size) {
        this.mem_size = mem_size;
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
