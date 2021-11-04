package org.firstinspires.ftc.teamcode.OpMode.StateMachine;

import java.util.Hashtable;

import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.IRobot;

public abstract class AStateMachine implements IStateMachine {
  protected final IRobot robot;
  protected final Hashtable<Integer, ARobotState> states;

  /**
   *
   * @param robot  A constructed IRobot
   * @param states A hashtable that corresponds an integer priority (starting at zero for highest
   *               priority and increasing to lower priorities) to a set of states for the robot.
   */
  public AStateMachine(IRobot robot, Hashtable<Integer, ARobotState> states) {
    this.robot = robot;
    this.states = states;
  }

}
