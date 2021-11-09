package org.firstinspires.ftc.teamcode.OpMode.UtilOpModes.UtilStates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.OpMode.StateMachine.GamepadButtons;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.IMU.IMUGyro;

public class GyroCalibrationTelemetryState extends ARobotState {

  private Collection<String> telemetryData;

  public GyroCalibrationTelemetryState() {
    this.telemetryData = new ArrayList<>();
    this.telemetryData.add("No gyro info added yet");
  }

  @Override
  public void receiveGyro(IMUGyro gyro) {
    this.telemetryData = gyro.getInformation();
  }

  @Override
  public boolean finishedExecution() {
    return true;
  }

  @Override
  public Collection<String> getTelemetry() {
    return this.telemetryData;
  }

  @Override
  public Collection<GamepadButtons> getButtons() {
    return new ArrayList<>();
  }
}
