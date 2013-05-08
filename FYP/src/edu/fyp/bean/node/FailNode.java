package edu.fyp.bean.node;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class FailNode extends EndNode{
    public void process(){
        System.out.println("Test FailNode process");
        this.setState("finish");
    }
}