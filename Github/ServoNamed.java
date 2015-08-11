package com.qualcomm.ftcrobotcontroller;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Alex on 7/18/2015.
 */
public class ServoNamed extends Servo{

    String name;

    public ServoNamed(String name, Servo servo){
        super(servo.getController(), servo.getPortNumber());
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
