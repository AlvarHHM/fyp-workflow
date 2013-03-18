package edu.fyp.bean.node;


public abstract class PathNode {
    private String state;
    private String nodeID;

    public abstract void process();

    public abstract String getState();

    public abstract void setState(String state);
}