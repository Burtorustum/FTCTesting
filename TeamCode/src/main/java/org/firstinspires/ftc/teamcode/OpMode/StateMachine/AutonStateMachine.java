package org.firstinspires.ftc.teamcode.OpMode.StateMachine;

import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.IRobot;

public class AutonStateMachine extends AStateMachine {

  public AutonStateMachine(IRobot robot, List<ARobotState> stateList) {
    super(robot, stateList);
  }

  @Override
  public List<String> iterate() {
    if (this.stateList.isEmpty()) {
      robot.stop();
    } else if (this.stateList.get(0).finishedExecution()) {
      this.stateList.remove(0);
    } else {
      this.robot.dispatchState(this.stateList.get(0));
      return this.stateList.get(0).getTelemetry();
    }
    return new ArrayList<>();
  }

}
