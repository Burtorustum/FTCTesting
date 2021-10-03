package org.firstinspires.ftc.teamcode.Robot.StateMachine;

import java.util.List;
import org.firstinspires.ftc.teamcode.Robot.IRobot;

public class AutonStateMachine {
  private IRobot robot;
  private List<IAutonState> stateList;

  public AutonStateMachine(IRobot robot, List<IAutonState> stateList) {
    this.robot = robot;
    this.stateList = stateList;

  }
}
