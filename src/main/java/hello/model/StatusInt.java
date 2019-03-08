package hello.model;

public class StatusInt {
    private int timestamp;
    private String status;

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

    public StatusInt(int timestamp, String status) {
        this.timestamp = timestamp;
        this.status = status;
    }
}
