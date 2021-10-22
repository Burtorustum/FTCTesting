package org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SubsystemImplementation.DriveTrains;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.Mode;
import org.firstinspires.ftc.teamcode.Hardware.StateMachine.IRobotController;
import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SensorImplementation.IOutputFunc;
import org.firstinspires.ftc.teamcode.MiniPID.MiniPID;

public class MecanumDriveTrain implements IDriveTrain {

  private DcMotor FL, BL, FR, BR;
  //TODO: Add actual vals
  private final int encoderTicksPerRotation = 1;
  private final int gearRatio = 1;
  // in inches
  private final int wheelDiameter = 4;

  public MecanumDriveTrain(HardwareMap hwMap, Mode mode) {
    switch (mode) {
      case AUTON:
        this.autoInit(hwMap);
        break;
      case TELEOP:
        this.teleopInit(hwMap);
        break;
    }
  }

  @Override
  public void autoInit(HardwareMap hwMap) {
    this.generalInit(hwMap);
  }

  @Override
  public void teleopInit(HardwareMap hwMap) {
    this.generalInit(hwMap);
  }

  private void generalInit(HardwareMap hwMap){
    this.FL = hwMap.get(DcMotor.class, "FL");
    this.BL = hwMap.get(DcMotor.class, "BL");
    this.FR = hwMap.get(DcMotor.class, "FR");
    this.BR = hwMap.get(DcMotor.class, "BR");

    this.FL.setDirection(Direction.FORWARD);
    this.BL.setDirection(Direction.FORWARD);
    this.FR.setDirection(Direction.REVERSE);
    this.BR.setDirection(Direction.REVERSE);

    this.FL.setMode(RunMode.RUN_USING_ENCODER);
    this.BL.setMode(RunMode.RUN_USING_ENCODER);
    this.FR.setMode(RunMode.RUN_USING_ENCODER);
    this.BR.setMode(RunMode.RUN_USING_ENCODER);
  }

  @Override
  public void autoInitLoop() {

  }

  @Override
  public void teleopInitLoop() {

  }

  @Override
  public void autoStart() {

  }

  @Override
  public void teleopStart() {

  }

  @Override
  public void stop() {
    FL.setPower(0);
    BL.setPower(0);
    FR.setPower(0);
    BR.setPower(0);
  }

  @Override
  public void dispatchState(IRobotController robotState) {
    robotState.receiveMecanumDriveTrain(this);
  }

  @Override
  public void driveStraight(double power) {
    FL.setPower(power);
    BL.setPower(power);
    FR.setPower(power);
    BR.setPower(power);
  }

  @Override
  public void turnLeft(double power) {
    FL.setPower(-power);
    BL.setPower(-power);
    FR.setPower(power);
    BR.setPower(power);
  }

  @Override
  public void turnRight(double power) {
    FL.setPower(power);
    BL.setPower(power);
    FR.setPower(-power);
    BR.setPower(-power);
  }

  @Override
  public void turnToHeading(double basePower, int targetHeading, int tolerance, MiniPID controller,
      IOutputFunc<Float> curHeading) {
    float heading = curHeading.getOutput();

  }

  public void tankDrive(double leftStick, double rightStick) {
    FL.setPower(-leftStick);
    BL.setPower(-leftStick);
    FR.setPower(-rightStick);
    BR.setPower(-rightStick);
  }

  public void strafeLeft(double power) {
    FL.setPower(power);
    BL.setPower(-power);
    FR.setPower(-power);
    BR.setPower(power);
  }

  public void strafeRight(double power) {
    FL.setPower(-power);
    BL.setPower(power);
    FR.setPower(power);
    BR.setPower(-power);
  }


  public void setFLPower(double power) {
    FL.setPower(power);
  }

  public void setBLPower(double power) {
    BL.setPower(power);
  }

  public void setFRPower(double power) {
    FR.setPower(power);
  }

  public void setBRPower(double power) {
    BR.setPower(power);
  }
}
