package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Instance {
    @Id
    private Double id;

    @Field
    private Double startTIme;
    @Field
    private Double endTime;

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Double getStartTIme() {
        return startTIme;
    }

    public void setStartTIme(Double startTIme) {
        this.startTIme = startTIme;
    }

    public Double getEndTime() {
        return endTime;
    }

    public void setEndTime(Double endTime) {
        this.endTime = endTime;
    }
}
