package org.firstinspires.ftc.teamcode.OpMode.StateMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.IRobot;

public class TeleopStateMachine extends AStateMachine {

  private boolean firstRun;

  public TeleopStateMachine(IRobot robot, List<ARobotState> stateList) {
    super(robot, stateList);
    this.firstRun = true;
  }

  @Override
  public List<String> iterate() {
    if (this.firstRun) {
      if (this.doButtonsOverlap()) {
        throw new IllegalArgumentException("States have overlapping button usage.");
      }
      this.firstRun = false;
    }


    List<String> telem = new ArrayList<>();
    for (ARobotState state : this.stateList) {
      robot.dispatchState(state);
      telem.addAll(state.getTelemetry());
    }
    return telem;
  }

  private boolean doButtonsOverlap() {
    HashSet<GamepadButtons> set = new HashSet<>();
    for (ARobotState state : this.stateList) {
      for (GamepadButtons button : state.getButtons()) {
        if (set.contains(button)) {
          return true;
        } else {
          set.add(button);
        }
      }
    }
    return false;
  }
}
