package org.firstinspires.ftc.teamcode.Subsystems.Mechanical.DriveTrains;

import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.util.Pair;
import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.StartParameters;
import org.firstinspires.ftc.teamcode.Subsystems.Mechanical.AMechanicalSubsystem;

public class MecanumDriveTrain extends AMechanicalSubsystem implements IDriveTrain {

  private final double encoderTicksPerRotation = 537.6;
  private final int gearRatio = 1;
  private final double wheelDiameter = 4; // in inches
  private DcMotorEx FL, BL, FR, BR;

  private boolean isInit = false;

  public MecanumDriveTrain(HardwareMap hwMap, StartParameters.Mode mode) {
    super(hwMap, mode);
  }

  @Override
  protected void autoInit(HardwareMap hwMap) {
    this.generalInit(hwMap);
    this.setMotorZeroPower(ZeroPowerBehavior.BRAKE);
  }

  @Override
  protected void teleopInit(HardwareMap hwMap) {
    this.generalInit(hwMap);
    this.setMotorZeroPower(ZeroPowerBehavior.BRAKE);
  }

  private void generalInit(HardwareMap hwMap) {
    this.FL = hwMap.get(DcMotorEx.class, "FL");
    this.BL = hwMap.get(DcMotorEx.class, "BL");
    this.FR = hwMap.get(DcMotorEx.class, "FR");
    this.BR = hwMap.get(DcMotorEx.class, "BR");

    this.FL.setDirection(Direction.FORWARD);
    this.BL.setDirection(Direction.FORWARD);
    this.FR.setDirection(Direction.REVERSE);
    this.BR.setDirection(Direction.REVERSE);

    this.setMotorRunMode(RunMode.RUN_USING_ENCODER);

    this.isInit = true;
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
  public void turnRight(double power) {
    FL.setPower(power);
    BL.setPower(power);
    FR.setPower(-power);
    BR.setPower(-power);
  }

  @Override
  public void setMotorPower(List<Pair<DTMotorPos, Double>> powers) {
    if (powers.size() != 4) {
      throw new IllegalArgumentException(
          "must give 4 power values and 4 proper motor positions for a mecanum drivetrain.");
    }
    for (Pair<DTMotorPos, Double> pair : powers) {
      switch (pair.fst) {
        case BACK_LEFT:
          this.BL.setPower(pair.snd);
          break;
        case BACK_RIGHT:
          this.BR.setPower(pair.snd);
          break;
        case FRONT_LEFT:
          this.FL.setPower(pair.snd);
          break;
        case FRONT_RIGHT:
          this.FR.setPower(pair.snd);
          break;
        default:
          throw new IllegalArgumentException(
              "Given motor position is not valid for mecanum DT: " + pair.fst.name());
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
  public void setTargetPosition(List<Pair<DTMotorPos, Integer>> targets) {
    if (targets.size() != 4) {
      throw new IllegalArgumentException(
          "must give exactly 4 target positions for a mecanum drivetrain.");
    }
    for (Pair<DTMotorPos, Integer> pair : targets) {
      switch (pair.fst) {
        case BACK_LEFT:
          this.BL.setTargetPosition(pair.snd);
          break;
        case BACK_RIGHT:
          this.BR.setTargetPosition(pair.snd);
          break;
        case FRONT_LEFT:
          this.FL.setTargetPosition(pair.snd);
          break;
        case FRONT_RIGHT:
          this.FR.setTargetPosition(pair.snd);
          break;
        default:
          throw new IllegalArgumentException(
              "Given motor position is not valid for mecanum DT: " + pair.fst.name());
      }
    }
  }

  @Override
  public List<Pair<DTMotorPos, Integer>> getCurrentPosition() {
    ArrayList<Pair<DTMotorPos, Integer>> positions = new ArrayList<>();

    positions.add(new Pair<>(DTMotorPos.FRONT_LEFT, this.FL.getCurrentPosition()));
    positions.add(new Pair<>(DTMotorPos.FRONT_RIGHT, this.FR.getCurrentPosition()));
    positions.add(new Pair<>(DTMotorPos.BACK_LEFT, this.BL.getCurrentPosition()));
    positions.add(new Pair<>(DTMotorPos.BACK_RIGHT, this.BR.getCurrentPosition()));

    return positions;
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
