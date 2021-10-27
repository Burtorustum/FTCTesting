package org.firstinspires.ftc.teamcode.OpMode.Teleop.TeleopStates;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;

public abstract class ATeleopState extends ARobotState {
  protected Gamepad gp1, gp2;

  public ATeleopState(Gamepad gp1, Gamepad gp2) {
    this.gp1 = gp1;
    this.gp2 = gp2;
  }

  @Override
  public boolean finishedExecution() {
    return false;
  }
}
