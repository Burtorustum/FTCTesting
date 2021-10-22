package org.firstinspires.ftc.teamcode.Hardware.StateMachine;

import com.qualcomm.robotcore.hardware.Gamepad;
import java.util.List;
import org.firstinspires.ftc.teamcode.RobotImplementations.IRobot;

public class TeleopStateMachine {
  private final IRobot robot;
  private final List<IAutonController> stateList;
  private Gamepad gp1, gp2;

  public TeleopStateMachine(IRobot robot, List<IAutonController> stateList, Gamepad gp1, Gamepad gp2) {
    this.robot = robot;
    this.stateList = stateList;
    this.gp1 = gp1;
    this.gp2 = gp2;
  }

  public void iterate() {
    if (this.stateList.isEmpty()) {
      robot.stop();
    } else if (this.stateList.get(0).finishedExecution()) {
      this.stateList.remove(0);
    } else {
      this.robot.teleopDispatchState(this.stateList.get(0), this.gp1, this.gp2);
    }
  }
}
