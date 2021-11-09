package org.firstinspires.ftc.teamcode.OpMode.UtilOpModes.UtilStates;

import com.sun.tools.javac.util.Pair;
import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.OpMode.StateMachine.GamepadButtons;
import org.firstinspires.ftc.teamcode.Subsystems.Mechanical.DriveTrains.DTMotorPos;
import org.firstinspires.ftc.teamcode.Subsystems.Mechanical.DriveTrains.MecanumDriveTrain;

public class EncoderTelemetryState extends ARobotState {

  private final List<String> telemetryData;

  public EncoderTelemetryState() {
    this.telemetryData = new ArrayList<>();
    this.telemetryData.add("No gyro info added yet");
  }

  // Example for mecanum DT but can add other subsystems with motors in them.
  @Override
  public void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain) {
    this.telemetryData.clear();
    for (Pair<DTMotorPos, Integer> pair : driveTrain.getCurrentPosition()) {
      this.telemetryData.add(pair.fst.name() + ": " + pair.snd);
    }
  }

  @Override
  public boolean finishedExecution() {
    return true;
  }

  @Override
  public List<String> getTelemetry() {
    return this.telemetryData;
  }

  @Override
  public List<GamepadButtons> getButtons() {
    return new ArrayList<>();
  }


}
