package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.OpMode.IRobotController;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.Mode;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.StartParameters;
import org.firstinspires.ftc.teamcode.Subsystems.ISubsystem;

public abstract class BaseRobot implements IRobot {

  private final Telemetry telemetry;
  private final List<ISubsystem> subsystemList;
  private final StartParameters params;

  public BaseRobot(HardwareMap hwMap, Telemetry telemetry, StartParameters params) {
    this.telemetry = telemetry;
    this.params = params;
    this.subsystemList = this.genSubsystems(hwMap, this.params.getMode());

    switch (this.params.getMode()) {
      case AUTON:
        this.autoInit(hwMap);
        break;
      case TELEOP:
        this.teleopInit(hwMap);
        break;
    }
  }

  /**
   * @param hwMap hardwaremap passed from opmode
   * @param mode  Auton or Teleop
   * @return List of subsystems in this robot, with ALL SENSORS EARLIER IN THE LIST THAN ALL
   * NON-SENSOR SUBSYSTEMS
   */
  abstract List<ISubsystem> genSubsystems(HardwareMap hwMap, Mode mode);

  private void autoInit(HardwareMap hwMap) {
    for (ISubsystem sub : subsystemList) {
      sub.autoInit(hwMap);
    }
  }

  private void teleopInit(HardwareMap hwMap) {
    for (ISubsystem sub : subsystemList) {
      sub.teleopInit(hwMap);
    }
  }

  @Override
  public void initLoop() {
    switch (this.params.getMode()) {
      case AUTON:
        this.autoInitLoop();
        break;
      case TELEOP:
        this.teleopInitLoop();
        break;
    }
  }

  private void autoInitLoop() {
    for (ISubsystem sub : subsystemList) {
      sub.autoInitLoop();
    }
  }

  private void teleopInitLoop() {
    for (ISubsystem sub : subsystemList) {
      sub.teleopInitLoop();
    }
  }

  @Override
  public void start() {
    switch (this.params.getMode()) {
      case AUTON:
        this.autoStart();
        break;
      case TELEOP:
        this.teleopStart();
        break;
    }
  }

  private void autoStart() {
    for (ISubsystem sub : subsystemList) {
      sub.autoStart();
    }
  }

  private void teleopStart() {
    for (ISubsystem sub : subsystemList) {
      sub.teleopStart();
    }
  }

  @Override
  public void stop() {
    for (ISubsystem sub : subsystemList) {
      sub.stop();
    }
  }

  @Override
  public void dispatchState(IRobotController robotState) {
    for (ISubsystem subsystem : this.subsystemList) {
      subsystem.dispatchState(robotState);
    }
  }


  @Override
  public void sendTelemetry(String msg, Object... data) {
    String line = msg;
    for (Object o : data) {
      line += o.toString() + ", ";
    }
    this.telemetry.addLine(line);
  }

  @Override
  public void updateTelemetry() {
    this.telemetry.update();
  }

  @Override
  public StartParameters getParams() {
    return this.params;
  }
}