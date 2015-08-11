package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Alex on 7/24/2015.
 */
public class TelemetryTest extends OpMode {

    String buttons;

    public void init(){

    }

    public void loop(){

        buttons = "Buttons: ";
        if(gamepad1.x){
            buttons = buttons + "x, ";
        }
        if(gamepad1.a){
            buttons = buttons + "a, ";
        }
        if(gamepad1.b){
            buttons = buttons + "b, ";
        }
        if(gamepad1.y){
            buttons = buttons + "y, ";
        }
        if(gamepad1.left_bumper){
            buttons = buttons + "LB, ";
        }
        if(gamepad1.right_bumper){
            buttons = buttons + "RB, ";
        }

        if(buttons.length() > 10){
            buttons = buttons.substring(0,buttons.length() -2);
        }

        telemetry.addData("Buttons", buttons);
        telemetry.addData("Right Trigger", "Right Trigger: " + Double.toString(gamepad1.right_trigger));
        telemetry.addData("Left Trigger", "Left Trigger: " + Double.toString(gamepad1.left_trigger));
        telemetry.addData("Right Y", "Right Y: " + Double.toString(gamepad1.right_stick_y));
        telemetry.addData("Left y", "Left Y: " + Double.toString(gamepad1.left_stick_y));

    }

    public void stop(){

    }
}
