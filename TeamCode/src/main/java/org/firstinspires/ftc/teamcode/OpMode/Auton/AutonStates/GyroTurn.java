package org.firstinspires.ftc.teamcode.OpMode.Auton.AutonStates;

import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.MiniPID.MiniPID;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.IMUGyro;
import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.Rev2M;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

public class GyroTurn extends ARobotState {

  private final double kp;
  private final double ki;
  private final double kd;
  private final int target;
  private final MiniPID pid;

  private double lastHeading;

  public GyroTurn(double kp, double ki, double kd, int target, boolean turnRight) {
    this.kp = kp;
    this.ki = ki;
    this.kd = kd;
    this.target = target;

    this.pid = new MiniPID(kp, ki, kd);
    this.pid.setSetpoint(this.target);
    this.pid.setSetpointRange(1);
    this.pid.setDirection(!turnRight); // can be changed based on how it turns
    // TODO: May need to check above line
    this.pid.setOutputLimits(-1, 1);
    this.pid.setOutputRampRate(.05);

    // Deafult heading is 0
    this.lastHeading = 0;
  }

  @Override
  public boolean finishedExecution() {
    return this.pid.getOutput() == 0;
  }

  @Override
  public List<String> getTelemetry() {
    List<String> telem = new ArrayList<String>();
    telem.add("PID output: " + this.pid.getOutput());
    telem.add("Heading: " + this.lastHeading);
    return telem;
  }

  @Override
  public void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain) {
    double pow = this.pid.getOutput(this.lastHeading);
    driveTrain.turnRight(pow);
  }

  @Override
  public void receiveGyro(IMUGyro gyro) {
    this.lastHeading = gyro.getOutput();
  }

}
