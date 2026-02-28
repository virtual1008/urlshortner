package com.Project.UrlShortner.id;

public class SnowflakeIdGenerator implements IdGenerator{

    private final Snowflake snowflake;

    public SnowflakeIdGenerator(long nodeId){
        this.snowflake = new Snowflake(nodeId);
    }
    @Override
    public long generatedId() {
        return snowflake.nextId();
    }
}
