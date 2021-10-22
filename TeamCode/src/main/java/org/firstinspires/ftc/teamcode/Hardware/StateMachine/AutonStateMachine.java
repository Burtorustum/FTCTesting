package org.firstinspires.ftc.teamcode.Hardware.StateMachine;

import java.util.List;
import org.firstinspires.ftc.teamcode.RobotImplementations.IRobot;

public class AutonStateMachine {
  private final IRobot robot;
  private final List<IAutonController> stateList;

  public AutonStateMachine(IRobot robot, List<IAutonController> stateList) {
    this.robot = robot;
    this.stateList = stateList;

  }

  public void iterate() {
    if (this.stateList.isEmpty()) {
      robot.stop();
    } else if (this.stateList.get(0).finishedExecution()) {
      this.stateList.remove(0);
    } else {
      this.robot.dispatchState(this.stateList.get(0));
    }


  }
}
