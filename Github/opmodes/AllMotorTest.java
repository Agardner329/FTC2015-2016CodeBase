package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.ftcrobotcontroller.Essentials;
import com.qualcomm.ftcrobotcontroller.DcMotorNamed;
import com.qualcomm.ftcrobotcontroller.ServoNamed;

import java.util.ArrayList;

public class AllMotorTest extends OpMode {

    ArrayList<DcMotorNamed> motors = new ArrayList<>();
    ArrayList<ServoNamed> servos = new ArrayList<>();

    int cycleCount = 0;
    int cyclePosition = 0;
    int cycleLength;

    boolean leftPressed = false;
    boolean rightPressed = false;

    public void init(){
        motors.add(new DcMotorNamed("FL Motor", hardwareMap.dcMotor.get("Motor_1_1")));
        motors.add(new DcMotorNamed("FR Motor", hardwareMap.dcMotor.get("Motor_1_2")));
        motors.add(new DcMotorNamed("BL Motor", hardwareMap.dcMotor.get("Motor_2_1"), DcMotorNamed.Direction.REVERSE));
        motors.add(new DcMotorNamed("BR Motor", hardwareMap.dcMotor.get("Motor_2_2"), DcMotorNamed.Direction.REVERSE));

        servos.add(new ServoNamed("FL Servo", hardwareMap.servo.get("Servo_1_1")));
        servos.add(new ServoNamed("FR Servo", hardwareMap.servo.get("Servo_1_2")));
        servos.add(new ServoNamed("BL Servo", hardwareMap.servo.get("Servo_1_3")));
        servos.add(new ServoNamed("BR Servo", hardwareMap.servo.get("Servo_1_4")));

        cycleLength = motors.size() + servos.size();
    }

    public void loop(){
        if(gamepad1.left_bumper && !leftPressed){
            cycleCount--;
            leftPressed = true;
        }
        if(!gamepad1.left_bumper && leftPressed){
            leftPressed = false;
        }
        if(gamepad1.right_bumper && !rightPressed){
            cycleCount++;
            rightPressed = true;
        }
        if(!gamepad1.right_bumper && rightPressed){
            rightPressed = false;
        }

        cyclePosition = cycleCount % cycleLength;

        if(cyclePosition % cycleLength < motors.size()){
            motors.get(cyclePosition).setPower(Essentials.cubeDZ(gamepad1.left_stick_y));
            if(cyclePosition != 0){
                motors.get(cyclePosition - 1).setPower(0);
            }
            if(cyclePosition != motors.size() - 1){
                motors.get(cyclePosition + 1).setPower(0);
            }

            telemetry.addData("Motor", "Motor: " + motors.get(cyclePosition).getName());
            telemetry.addData("PowPos", "Power: " + Double.toString(motors.get(cyclePosition).getPower()));
        }else{
            if(gamepad1.dpad_up){
                if(servos.get(cyclePosition-motors.size()).getPosition() < 0.94) {
                    servos.get(cyclePosition - motors.size()).setPosition(servos.get(cyclePosition - motors.size()).getPosition() + 0.05);
                }
            }else if(gamepad1.dpad_down){
                if(servos.get(cyclePosition-motors.size()).getPosition() > 0.06) {
                    servos.get(cyclePosition - motors.size()).setPosition(servos.get(cyclePosition - motors.size()).getPosition() - 0.05);
                }
            }else if(gamepad1.y){
                if(servos.get(cyclePosition-motors.size()).getPosition() < 0.994) {
                    servos.get(cyclePosition - motors.size()).setPosition(servos.get(cyclePosition - motors.size()).getPosition() + 0.005);
                }
            }else if(gamepad1.a) {
                if(servos.get(cyclePosition-motors.size()).getPosition() > 0.006) {
                    servos.get(cyclePosition - motors.size()).setPosition(servos.get(cyclePosition - motors.size()).getPosition() - 0.005);
                }
            }

            telemetry.addData("Motor", "Servo: " + servos.get(cyclePosition - motors.size()).getName());
            telemetry.addData("PowPos", "Position: " + Double.toString(servos.get(cyclePosition - motors.size()).getPosition()));
        }
    }

    public void stop(){

    }
}
