package com.github.newlight77.infrastructure.eventstore;

public class EventStoreFileConfig {
    private String configPath = "./tmp/events/checkin/";

    public String configPath() {
        return configPath;
    }

    public void configPath(String configPath) {
        this.configPath = configPath;
    }
}
