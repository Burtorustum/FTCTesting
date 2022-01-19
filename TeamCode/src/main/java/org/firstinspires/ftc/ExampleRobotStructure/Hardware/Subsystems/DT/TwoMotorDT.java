package org.firstinspires.ftc.ExampleRobotStructure.Hardware.Subsystems.DT;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Utility.DropIns.PIDController;

public class TwoMotorDT extends TankDriveTrain{
    private final DcMotor left;
    private final DcMotor right;
    private boolean runToPos;

    private final PIDController leftPID;
    private final PIDController rightPID;

    public TwoMotorDT(HardwareMap hwMap, Telemetry telemetry) {
        super(telemetry);
        left = hwMap.get(DcMotor.class, "left");
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setDirection(DcMotorSimple.Direction.REVERSE);

        right = hwMap.get(DcMotor.class, "right");
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setDirection(DcMotorSimple.Direction.FORWARD);

        runToPos = true;

        leftPID = new PIDController(0,0,0,0);
        rightPID = new PIDController(0,0,0,0);
    }

    @Override
    public void setTarget(int target) {
        left.setTargetPosition(target);
        right.setTargetPosition(target);
    }

    @Override
    public void moveToTarget() {
        if (!runToPos) {
            left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            runToPos = true;
        }

        //TODO

    }

    @Override
    public boolean arrived() {
        return Math.max(
                Math.abs(left.getCurrentPosition() - left.getTargetPosition()),
                Math.abs(right.getCurrentPosition() - right.getTargetPosition()))
                < 10;
    }

    @Override
    public void driveLeft(double pow) {
        if (runToPos) {
            left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            runToPos = false;
        }
        left.setPower(pow);
    }

    @Override
    public void driveRight(double pow) {
        if (runToPos) {
            left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            runToPos = false;
        }
        right.setPower(pow);
    }

    @Override
    public void drive(double pow) {
        if (runToPos) {
            left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            runToPos = false;
        }
        left.setPower(pow);
        right.setPower(pow);
    }

    @Override
    public void turnClockwise(double pow) {
        left.setPower(pow);
        right.setPower(-pow);
    }

    @Override
    public void updateState() {

    }

    @Override
    public void updateTeleopState(Gamepad gp1, Gamepad gp2) {
        left.setPower(-gp1.left_stick_y);
        right.setPower(-gp1.right_stick_y);
    }

    @Override
    public void startTeleop() {
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        runToPos = false;
    }

    @Override
    public void startAuton() {}

    @Override
    public void stop() {
        left.setPower(0);
        right.setPower(0);
    }
}
