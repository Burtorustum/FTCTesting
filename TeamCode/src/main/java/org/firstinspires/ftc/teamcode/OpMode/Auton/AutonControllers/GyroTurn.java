package org.firstinspires.ftc.teamcode.OpMode.Auton.AutonControllers;

import org.firstinspires.ftc.teamcode.MiniPID.MiniPID;
import org.firstinspires.ftc.teamcode.OpMode.IRobotController;
import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.IMUSensor;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

public class GyroTurn implements IRobotController {

  private final double kp;
  private final double ki;
  private final double kd;
  private final int target;
  private final MiniPID pid;

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
