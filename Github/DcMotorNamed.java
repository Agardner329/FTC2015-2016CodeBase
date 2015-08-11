package com.qualcomm.ftcrobotcontroller;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DcMotorNamed extends DcMotor{
    String name;
    DcMotor motor;

    public DcMotorNamed(String name, DcMotor dcmotor) {
        super(dcmotor.getController(), dcmotor.getPortNumber());
        this.name = name;
    }

    public DcMotorNamed(String name, DcMotor dcmotor, DcMotor.Direction direction) {
        super(dcmotor.getController(), dcmotor.getPortNumber(), direction);
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
