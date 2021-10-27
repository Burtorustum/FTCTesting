package org.firstinspires.ftc.teamcode.OpMode.StateMachine;

import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.IRobot;

public abstract class AStateMachine implements IStateMachine {
  protected final IRobot robot;
  protected final List<ARobotState> stateList;

  public AStateMachine(IRobot robot, List<ARobotState> stateList) {
    this.robot = robot;
    this.stateList = stateList;
  }

}
