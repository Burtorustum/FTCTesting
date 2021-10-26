package org.firstinspires.ftc.teamcode.Auton.AutonControllers;

import org.firstinspires.ftc.teamcode.SubsystemsAndSensors.SensorImplementation.IMUSensor;
import org.firstinspires.ftc.teamcode.SubsystemsAndSensors.SubsystemImplementation.DriveTrains.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.MiniPID.MiniPID;

public class GyroTurn implements IAutonController {

  private double kp, ki, kd;
  private int target;
  private MiniPID pid;

  public GyroTurn(double kp, double ki, double kd, int target) {
    this.kp = kp;
    this.ki = ki;
    this.kd = kd;
    this.target = target;

    this.pid = new MiniPID(kp, ki, kd);
    this.pid.setSetpoint(this.target);
    this.pid.setSetpointRange(1);
    this.pid.setDirection(false); // can be changed based on how it turns
    // TODO: May need to add parameter to constructor for above
    this.pid.setOutputLimits(-1, 1);
    this.pid.setOutputRampRate(.05);
  }

  @Override
  public boolean finishedExecution() {
    return this.pid.getOutput() == 0;
  }

  @Override
  public void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain) {
    double pow = this.pid.getOutput();
    driveTrain.turnRight(pow);
  }

  @Override
  public void receiveGyro(IMUSensor gyro) {
    this.pid.getOutput(gyro.getOutput());
  }


}