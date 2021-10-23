package org.firstinspires.ftc.teamcode.SubsystemsAndSensors.SubsystemImplementation.DriveTrains;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.RobotParameters.Mode;
import org.firstinspires.ftc.teamcode.StateMachine.IRobotController;

public class MecanumDriveTrain implements IDriveTrain {

  private DcMotor FL, BL, FR, BR;
  private final double encoderTicksPerRotation = 537.6;
  private final int gearRatio = 1;
  // in inches
  private final double wheelDiameter = 4;

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

    this.setMotorRunMode(RunMode.RUN_USING_ENCODER);
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
  public void setMotorPower(DTMotorPos[] positions, double[] powers) {
    if (powers.length != 4 || positions.length != 4) {
      throw new IllegalArgumentException("must give 4 power values and 4 proper motor positions for a mecanum drivetrain.");
    }
    for (int i = 0; i < 4; i++) {
      switch(positions[i]) {
        case BACK_LEFT:
          this.BL.setPower(powers[i]);
          break;
        case BACK_RIGHT:
          this.BR.setPower(powers[i]);
          break;
        case FRONT_LEFT:
          this.FL.setPower(powers[i]);
          break;
        case FRONT_RIGHT:
          this.FR.setPower(powers[i]);
          break;
        default:
          throw new IllegalArgumentException("Given position is not valid for mecanum DT: " + positions[i].name());
      }
    }

  }

  @Override
  public void setMotorRunMode(RunMode motorMode) {
    this.BL.setMode(motorMode);
    this.BR.setMode(motorMode);
    this.FL.setMode(motorMode);
    this.FR.setMode(motorMode);
  }

  @Override
  public void setMotorZeroPower(ZeroPowerBehavior behavior) {
    this.BL.setZeroPowerBehavior(behavior);
    this.BR.setZeroPowerBehavior(behavior);
    this.FL.setZeroPowerBehavior(behavior);
    this.FR.setZeroPowerBehavior(behavior);
  }

  public void tankDrive(double leftStickY, double rightStickY) {
    FL.setPower(-leftStickY);
    BL.setPower(-leftStickY);
    FR.setPower(-rightStickY);
    BR.setPower(-rightStickY);
  }

  // TODO: Test
  public void fieldCentricDrive(double lStickY, double lStickX, double rStickX, double curHeading) {
    // Get the controller values
    double forward = (-1) * lStickY;
    double strafe = lStickX;
    double clockwise = rStickX;

    // Apply the turn modifier k
    clockwise *= 1; //TODO: Test and see if should be lowered

    // Convert to Radians for Math.sin/cos
    double orient = Math.toRadians(curHeading);
    double sin = Math.sin(orient);
    double cos = Math.cos(orient);

    // Apply the rotation matrix
    double temp = forward * cos - strafe * sin;
    strafe = forward * sin + strafe * cos;
    forward = temp;

    // Set power values
    double flPow = forward + clockwise + strafe;
    double frPow = forward - clockwise - strafe;
    double rlPow = forward + clockwise - strafe;
    double rrPow = forward - clockwise + strafe;

    double max = Math.max(1, Math.max(flPow, Math.max(frPow, Math.max(rlPow, rrPow))));

    // Clip power values to within acceptable ranges for the motors
    flPow /= max;
    frPow /= max;
    rlPow /= max;
    rrPow /= max;

    // Send power values to motors
    FL.setPower(flPow);
    BL.setPower(rlPow);
    FR.setPower(frPow);
    BR.setPower(rrPow);
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
