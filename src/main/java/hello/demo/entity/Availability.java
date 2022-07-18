package hello.demo.entity;

public enum Availability {
    AVAILABLE("Available"), NOT_AVAILABLE("Not available");

    private String status;

    Availability(String envUrl) {
        this.status = envUrl;
    }

    public String getStatus() {
        return status;
    }
}
