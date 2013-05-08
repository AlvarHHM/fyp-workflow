package edu.fyp.bean.node;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class SuccessNode extends EndNode{
    public void process(){
        System.out.println("Test SuccessNode process");
        this.setState("finish");
    }
}