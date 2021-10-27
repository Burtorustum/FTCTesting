package org.firstinspires.ftc.teamcode.OpMode.StateMachine;

import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.IRobot;

public class TeleopStateMachine extends AStateMachine {

  public TeleopStateMachine(IRobot robot, List<ARobotState> stateList) {
    super(robot, stateList);
  }

  @Override
  public List<String> iterate() {
    List<String> telem = new ArrayList<>();
    for (ARobotState state : this.stateList) {
      robot.dispatchState(state);
      telem.addAll(state.getTelemetry());
    }
    return telem;
  }
}
