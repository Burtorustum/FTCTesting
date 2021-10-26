package org.firstinspires.ftc.teamcode.OpMode.StateMachine;

import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.IRobotController;
import org.firstinspires.ftc.teamcode.Robot.IRobot;

public abstract class AStateMachine implements IStateMachine {
  protected final IRobot robot;
  protected final List<IRobotController> stateList;

  public AStateMachine(IRobot robot, List<IRobotController> stateList) {
    this.robot = robot;
    this.stateList = stateList;
  }
}
