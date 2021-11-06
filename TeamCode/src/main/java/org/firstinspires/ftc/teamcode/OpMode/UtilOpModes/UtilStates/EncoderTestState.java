package org.firstinspires.ftc.teamcode.OpMode.UtilOpModes.UtilStates;

import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.OpMode.StateMachine.GamepadButtons;

public class EncoderTestState extends ARobotState {

  public EncoderTestState() {
  }

  @Override
  public boolean finishedExecution() {
    return false;
  }

  @Override
  public List<String> getTelemetry() {
    return null;
  }

  @Override
  public List<GamepadButtons> getButtons() {
    return new ArrayList<>();
  }


}
