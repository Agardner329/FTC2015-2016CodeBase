package com.qualcomm.ftcrobotcontroller;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.ArrayList;

public class Essentials {

    public static final float joystickDZ = (float)0.2;
    public static final float triggerDZ = (float)0.1;

    public static float cubeDZ(float input) {
        if (Math.abs(input) < Essentials.joystickDZ) {
            return 0;
        } else {
            return (float) (Math.pow(input, 3));
        }
    }
}
