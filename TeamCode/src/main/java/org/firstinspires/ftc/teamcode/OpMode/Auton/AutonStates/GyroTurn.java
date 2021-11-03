package org.firstinspires.ftc.teamcode.OpMode.Auton.AutonStates;

import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.Utility.MiniPID.MiniPIDEx;
import org.firstinspires.ftc.teamcode.Subsystems.ISubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.IMUGyro;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

public class GyroTurn extends AAutonState {

  private final MiniPIDEx pid;

  private double lastHeading;
  private double lastOutput;
  private double tolerance;
  private int withinToleranceCount;

  /**
   * Construct a new GyroTurn
   * @param kp P-coefficient
   * @param ki I-coefficient
   * @param kd D-coefficient
   * @param target target heading in degrees 0 <= target < 360
   */
  public GyroTurn(double kp, double ki, double kd, double target, double tolerance, boolean turnRight) {

    this.pid = new MiniPIDEx(kp, ki, kd);
    this.pid.setSetpoint(target);
    this.pid.setDirection(!turnRight); // can be changed based on how it turns
    // TODO: May need to check above line
    this.pid.setOutputLimits(-.7, .7);

    // Default heading is 0
    this.lastHeading = 0;
    this.lastOutput = 0;
    this.withinToleranceCount = 0;
    this.tolerance = tolerance;
  }

  @Override
  public boolean finishedExecution() {
    return this.withinToleranceCount >= 5;
  }

  @Override
  public List<String> getTelemetry() {
    List<String> telem = new ArrayList<>();
    telem.add("PID output: " + this.lastOutput);
    telem.add("Heading: " + this.lastHeading);
    telem.add("Within tolerance? " + this.finishedExecution());
    return telem;
  }

  @Override
  public List<Class<? extends ISubsystem>> getSubsystems() {
    List<Class<? extends ISubsystem>> subsystemClasses = new ArrayList<>();
    subsystemClasses.add(IMUGyro.class);
    subsystemClasses.add(MecanumDriveTrain.class);

    return subsystemClasses;
  }

  @Override
  public void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain) {
    double pow = this.pid.getOutputGyro(this.lastHeading);
    if (pow < this.tolerance) {
      this.withinToleranceCount++;
    } else {
      this.withinToleranceCount = 0;
    }
    this.lastOutput = pow;
    driveTrain.turnRight(pow);
  }

  @Override
  public void receiveGyro(IMUGyro gyro) {
    this.lastHeading = gyro.getOutput();
  }

  public void setKVals(double kp, double ki, double kd) {
    this.pid.setPID(kp, ki, kd);
  }

  public void setTarget(double target) {
    this.pid.setSetpoint(target);
  }

  public void changeDirection(boolean reverse) {
    this.pid.setDirection(reverse);
  }

  public void increaseTolerance(double tolerance) {
    this.tolerance = tolerance;
  }

}
