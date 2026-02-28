package com.Project.UrlShortner.id;

public class Snowflake {
    private final long nodeId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public Snowflake(long nodeId){
        this.nodeId = nodeId;
    }

    public synchronized long nextId(){
        long currentTimestamp = System.currentTimeMillis();

        if(currentTimestamp==lastTimestamp){
            sequence = (sequence+1)&4095;
            if(sequence==0){
                while (currentTimestamp==lastTimestamp){
                    currentTimestamp = System.currentTimeMillis();
                }
            }
        }else{
            sequence = 0;
        }
        lastTimestamp = currentTimestamp;
        return (currentTimestamp<<22) | (nodeId<<12) | sequence;
    }
}
