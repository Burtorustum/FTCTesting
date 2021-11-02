package org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.StartParameters;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.ASubsystem;

public class MecanumDriveTrain extends ASubsystem implements IDriveTrain {

  private final double encoderTicksPerRotation = 537.6;
  private final int gearRatio = 1;
  private final double wheelDiameter = 4; // in inches
  private DcMotor FL, BL, FR, BR;

  private boolean isInit = false;

  public MecanumDriveTrain(HardwareMap hwMap, StartParameters.Mode mode) {
    super(hwMap, mode);
  }

  @Override
  public void autoInit(HardwareMap hwMap) {
    this.generalInit(hwMap);
    this.setMotorZeroPower(ZeroPowerBehavior.BRAKE);
  }

  @Override
  public void teleopInit(HardwareMap hwMap) {
    this.generalInit(hwMap);
    this.setMotorZeroPower(ZeroPowerBehavior.BRAKE);
  }

  private void generalInit(HardwareMap hwMap) {
    this.FL = hwMap.get(DcMotor.class, "FL");
    this.BL = hwMap.get(DcMotor.class, "BL");
    this.FR = hwMap.get(DcMotor.class, "FR");
    this.BR = hwMap.get(DcMotor.class, "BR");

    this.FL.setDirection(Direction.FORWARD);
    this.BL.setDirection(Direction.FORWARD);
    this.FR.setDirection(Direction.REVERSE);
    this.BR.setDirection(Direction.REVERSE);

    this.setMotorRunMode(RunMode.RUN_USING_ENCODER);

    this.isInit = true;
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
  public void dispatchState(ARobotState robotState) {
    robotState.receiveMecanumDriveTrain(this);
  }

  @Override
  public boolean isInitialized() {
    return this.isInit;
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
      throw new IllegalArgumentException(
          "must give 4 power values and 4 proper motor positions for a mecanum drivetrain.");
    }
    for (int i = 0; i < 4; i++) {
      switch (positions[i]) {
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
          throw new IllegalArgumentException(
              "Given position is not valid for mecanum DT: " + positions[i].name());
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

  @Override
  public boolean runToDistance(int distance, DistanceUnit units) {
    return false;
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
