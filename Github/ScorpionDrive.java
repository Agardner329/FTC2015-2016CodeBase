package com.qualcomm.ftcrobotcontroller;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ScorpionDrive{
    private static final float FL_YG_POS = (float)0.896;
    private static final float FR_YG_POS = (float)0.254;
    private static final float BL_YG_POS = (float)0.245;
    private static final float BR_YG_POS = (float)0.696;

    private static final float FL_RB_POS = (float)0.217;
    private static final float FR_RB_POS = (float)0.879;
    private static final float BL_RB_POS = (float)0.9;
    private static final float BR_RB_POS = (float)0.046;

    private static final float FL_TURN_POS = (float)0.571;
    private static final float FR_TURN_POS = (float)0.575;
    private static final float BL_TURN_POS = (float)0.575;
    private static final float BR_TURN_POS = (float)0.387;

    private DcMotor mFL;
    private DcMotor mFR;
    private DcMotor mBL;
    private DcMotor mBR;

    private Servo sFL;
    private Servo sFR;
    private Servo sBL;
    private Servo sBR;

    private int direction;

    private final int yellow = 0;
    private final int red = 1;
    private final int green = 2;
    private final int blue = 3;

    private final int driving = 0;
    private final int turning = 1;
    private final int strafing = 2;

    public ScorpionDrive(DcMotor mFLinput, DcMotor mFRinput, DcMotor mBLinput, DcMotor mBRinput, Servo sFLinput, Servo sFRinput, Servo sBLinput, Servo sBRinput){

        mFL = mFLinput;
        mFR = mFRinput;
        mBL = mBLinput;
        mBR = mBRinput;

        mBL.setDirection(DcMotor.Direction.REVERSE);
        mBR.setDirection(DcMotor.Direction.REVERSE);

        sFL = sFLinput;
        sFR = sFRinput;
        sBL = sBLinput;
        sBR = sBRinput;
    }

    public void setDirection(int input){
        direction = input;
    }

    private void setStatus(int input){
        switch(input){
            case driving:
                switch(direction){
                    case yellow:
                        sFL.setPosition(ScorpionDrive.FL_YG_POS);
                        sFR.setPosition(ScorpionDrive.FR_YG_POS);
                        sBL.setPosition(ScorpionDrive.BL_YG_POS);
                        sBR.setPosition(ScorpionDrive.BR_YG_POS);
                        break;
                    case red:
                        sFL.setPosition(ScorpionDrive.FL_RB_POS);
                        sFR.setPosition(ScorpionDrive.FR_RB_POS);
                        sBL.setPosition(ScorpionDrive.BL_RB_POS);
                        sBR.setPosition(ScorpionDrive.BR_RB_POS);
                        break;
                    case green:
                        sFL.setPosition(ScorpionDrive.FL_YG_POS);
                        sFR.setPosition(ScorpionDrive.FR_YG_POS);
                        sBL.setPosition(ScorpionDrive.BL_YG_POS);
                        sBR.setPosition(ScorpionDrive.BR_YG_POS);
                        break;
                    case blue:
                        sFL.setPosition(ScorpionDrive.FL_RB_POS);
                        sFR.setPosition(ScorpionDrive.FR_RB_POS);
                        sBL.setPosition(ScorpionDrive.BL_RB_POS);
                        sBR.setPosition(ScorpionDrive.BR_RB_POS);
                        break;
                }
                break;
            case strafing:
                switch(direction){
                    case red:
                        sFL.setPosition(ScorpionDrive.FL_YG_POS);
                        sFR.setPosition(ScorpionDrive.FR_YG_POS);
                        sBL.setPosition(ScorpionDrive.BL_YG_POS);
                        sBR.setPosition(ScorpionDrive.BR_YG_POS);
                        break;
                    case green:
                        sFL.setPosition(ScorpionDrive.FL_RB_POS);
                        sFR.setPosition(ScorpionDrive.FR_RB_POS);
                        sBL.setPosition(ScorpionDrive.BL_RB_POS);
                        sBR.setPosition(ScorpionDrive.BR_RB_POS);
                        break;
                    case blue:
                        sFL.setPosition(ScorpionDrive.FL_YG_POS);
                        sFR.setPosition(ScorpionDrive.FR_YG_POS);
                        sBL.setPosition(ScorpionDrive.BL_YG_POS);
                        sBR.setPosition(ScorpionDrive.BR_YG_POS);
                        break;
                    case yellow:
                        sFL.setPosition(ScorpionDrive.FL_RB_POS);
                        sFR.setPosition(ScorpionDrive.FR_RB_POS);
                        sBL.setPosition(ScorpionDrive.BL_RB_POS);
                        sBR.setPosition(ScorpionDrive.BR_RB_POS);
                        break;
                }
                break;
            case turning:
                sFL.setPosition(ScorpionDrive.FL_TURN_POS);
                sFR.setPosition(ScorpionDrive.FR_TURN_POS);
                sBL.setPosition(ScorpionDrive.BL_TURN_POS);
                sBR.setPosition(ScorpionDrive.BR_TURN_POS);
                break;
        }
    }
    
    public void turn(float input){
        setStatus(turning);
        
        mFL.setPower(input);
        mFR.setPower(input);
        mBL.setPower(input);
        mBR.setPower(input);
    }
    
    public void strafe(float input){
        setStatus(strafing);

        switch (direction) {
            case yellow:
                mFL.setPower(input);
                mFR.setPower(input);
                mBL.setPower(-input);
                mBR.setPower(-input);
                break;
            case red:
                mFL.setPower(-input);
                mFR.setPower(input);
                mBL.setPower(-input);
                mBR.setPower(input);
                break;
            case green:
                mFL.setPower(-input);
                mFR.setPower(-input);
                mBL.setPower(input);
                mBR.setPower(input);
                break;
            case blue:
                mFL.setPower(input);
                mFR.setPower(-input);
                mBL.setPower(input);
                mBR.setPower(-input);
                break;
        }
    }
    
    public void drive(float inputLeft, float inputRight){
        setStatus(driving);
        
        switch (direction) {
            case yellow:
                mFL.setPower(inputLeft);
                mFR.setPower(-inputRight);
                mBL.setPower(inputLeft);
                mBR.setPower(-inputRight);
                break;
            case red:
                mFL.setPower(inputLeft);
                mFR.setPower(inputLeft);
                mBL.setPower(-inputRight);
                mBR.setPower(-inputRight);
                break;
            case green:
                mFL.setPower(-inputRight);
                mFR.setPower(inputLeft);
                mBL.setPower(-inputRight);
                mBR.setPower(inputLeft);
                break;
            case blue:
                mFL.setPower(-inputRight);
                mFR.setPower(-inputRight);
                mBL.setPower(inputLeft);
                mBR.setPower(inputLeft);
                break;
        }
    }

    public void stop(){
        mFL.setPower(0);
        mFR.setPower(0);
        mBL.setPower(0);
        mBR.setPower(0);
    }


}

