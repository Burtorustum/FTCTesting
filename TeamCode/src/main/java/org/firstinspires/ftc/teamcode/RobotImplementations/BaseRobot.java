package org.firstinspires.ftc.teamcode.RobotImplementations;

import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.Mode;
import org.firstinspires.ftc.teamcode.Hardware.StateMachine.IRobotController;
import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SensorImplementation.ISensor;
import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SubsystemImplementation.ISubsystem;

public abstract class BaseRobot implements IRobot {
  private final Telemetry telemetry;
  private final List<ISensor> sensorList;
  private final List<ISubsystem> subsystemList;
  private final Mode mode;

  public BaseRobot(HardwareMap hwMap, Telemetry telemetry, Mode mode) {
    this.telemetry = telemetry;
    this.sensorList = this.genSensors(hwMap, mode);
    this.subsystemList = this.genSubsystems(hwMap, mode);
    this.mode = mode; //TODO: MODE SWITCH FOR EACH INIT/INITLOOP/START/ETC
    switch (mode) {
      case AUTON:
        this.autoInit(hwMap);
        break;
      case TELEOP:
        this.teleopInit(hwMap);
        break;
    }
  }

  abstract List<ISensor> genSensors(HardwareMap hwMap, Mode mode);
  abstract List<ISubsystem> genSubsystems(HardwareMap hwMap, Mode mode);

  @Override
  public void autoInit(HardwareMap hwMap) {
    for (ISubsystem sub : subsystemList) {
      sub.autoInit(hwMap);
    }

  }

  @Override
  public void teleopInit(HardwareMap hwMap) {
    for (ISubsystem sub : subsystemList) {
      sub.teleopInit(hwMap);
    }
  }

  @Override
  public void autoInitLoop() {
    for (ISubsystem sub : subsystemList) {
      sub.autoInitLoop();
    }
  }

  @Override
  public void teleopInitLoop() {
    for (ISubsystem sub : subsystemList) {
      sub.teleopInitLoop();
    }
  }

  @Override
  public void autoStart() {
    for (ISubsystem sub : subsystemList) {
      sub.autoStart();
    }
  }

  @Override
  public void teleopStart() {
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


}