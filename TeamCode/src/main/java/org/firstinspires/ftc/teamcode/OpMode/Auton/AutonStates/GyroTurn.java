package org.firstinspires.ftc.teamcode.OpMode.Auton.AutonStates;

import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.Subsystems.Mechanical.DriveTrains.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.IMU.IMUGyro;
import org.firstinspires.ftc.teamcode.Utility.DropIns.PIDController;

public class GyroTurn extends AAutonState {

  private final PIDController pid;

  private double lastHeading;
  private double lastOutput;
  private double tolerance;
  private int withinToleranceCount;

  /**
   * Construct a new GyroTurn
   *
   * @param kp     P-coefficient
   * @param ki     I-coefficient
   * @param kd     D-coefficient
   * @param target target heading in degrees 0 <= target < 360
   */
  public GyroTurn(double kp, double ki, double kd, double target, double tolerance) {

    this.pid = new PIDController(kp, ki, kd ,target);
    this.pid.newTarget(target);
    //this.pid.setMaxErrorSum();

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
  public void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain) {
    double pow = this.pid.updateAngle(this.lastHeading);
    if (pid.withinTolerance(this.tolerance)) {
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
    this.pid.setGains(kp, ki, kd);
  }

  public void setTarget(double target) {
    this.pid.newTarget(target);
  }
}
