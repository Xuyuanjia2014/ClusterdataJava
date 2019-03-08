package hello.model;

public class UsageInt {
    private int timestamp;
    private String usage;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }


    public UsageInt(int timestamp, String usage) {
        this.timestamp = timestamp;
        this.usage = usage;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }
}
