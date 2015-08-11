package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.ftcrobotcontroller.ScorpionDrive;
import com.qualcomm.ftcrobotcontroller.Essentials;

public class ScorpionOp extends OpMode {

    ScorpionDrive scorpionDrive;

    private final int yellow = 0;
    private final int red = 1;
    private final int green = 2;
    private final int blue = 3;

    private final float DRIVING_HEIGHT_LIMIT = (float)0.2;

    public void init() {

        scorpionDrive = new ScorpionDrive(
                hardwareMap.dcMotor.get("Motor_1_1"),
                hardwareMap.dcMotor.get("Motor_1_2"),
                hardwareMap.dcMotor.get("Motor_2_1"),
                hardwareMap.dcMotor.get("Motor_2_2"),
                hardwareMap.servo.get("Servo_1_1"),
                hardwareMap.servo.get("Servo_1_2"),
                hardwareMap.servo.get("Servo_1_3"),
                hardwareMap.servo.get("Servo_1_4")
        );

        scorpionDrive.setDirection(yellow);
        scorpionDrive.stop();

    }

    public void loop() {

        //Direction Register
        if (gamepad1.y) {
            scorpionDrive.setDirection(yellow);
        } else if (gamepad1.b) {
            scorpionDrive.setDirection(red);
        } else if (gamepad1.a) {
            scorpionDrive.setDirection(green);
        } else if (gamepad1.x) {
            scorpionDrive.setDirection(blue);
        }

        //Status and motor Register
        if (gamepad1.left_trigger > Essentials.triggerDZ) {
            scorpionDrive.turn(Essentials.cubeDZ(-gamepad1.left_trigger));//turning
        }else if(gamepad1.right_trigger > Essentials.triggerDZ) {
            scorpionDrive.turn(Essentials.cubeDZ(gamepad1.right_trigger));//turning
        }else if (Math.abs(gamepad1.left_stick_x) > Essentials.joystickDZ && Math.abs(gamepad1.left_stick_y) < DRIVING_HEIGHT_LIMIT
                && Math.abs(gamepad1.right_stick_x) > Essentials.joystickDZ && Math.abs(gamepad1.right_stick_y) < DRIVING_HEIGHT_LIMIT) {//strafing
            scorpionDrive.strafe(Essentials.cubeDZ(gamepad1.left_stick_x));
        } else if(Math.abs(gamepad1.left_stick_y) > DRIVING_HEIGHT_LIMIT || Math.abs(gamepad1.right_stick_y) > DRIVING_HEIGHT_LIMIT){//Driving
            scorpionDrive.drive(Essentials.cubeDZ(-gamepad1.left_stick_y), Essentials.cubeDZ(-gamepad1.right_stick_y));
        }else{//stop
            scorpionDrive.stop();
        }
    }

    public void stop() {

    }
}