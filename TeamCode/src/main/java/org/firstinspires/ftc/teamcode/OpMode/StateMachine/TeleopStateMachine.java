package org.firstinspires.ftc.teamcode.OpMode.StateMachine;

import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.IRobotController;
import org.firstinspires.ftc.teamcode.Robot.IRobot;

public class TeleopStateMachine extends AStateMachine {

  public TeleopStateMachine(IRobot robot, List<IRobotController> stateList) {
    super(robot, stateList);
  }

  @Override
  public void iterate() {
    for (IRobotController state : this.stateList) {
      robot.dispatchState(state);
    }
  }
}
