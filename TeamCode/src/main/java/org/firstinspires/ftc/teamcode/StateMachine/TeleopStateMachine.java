package org.firstinspires.ftc.teamcode.StateMachine;

import com.qualcomm.robotcore.hardware.Gamepad;
import java.util.List;
import org.firstinspires.ftc.teamcode.RobotImplementations.IRobot;

public class TeleopStateMachine {
  private final IRobot robot;
  private final List<IRobotController> stateList;

  public TeleopStateMachine(IRobot robot, List<IRobotController> stateList) {
    this.robot = robot;
    this.stateList = stateList;
  }

  public void iterate() {
    for (IRobotController state : this.stateList) {
      robot.dispatchState(state);
    }
  }
}
