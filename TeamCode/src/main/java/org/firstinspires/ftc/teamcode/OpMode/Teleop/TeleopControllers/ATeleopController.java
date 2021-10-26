package org.firstinspires.ftc.teamcode.OpMode.Teleop.TeleopControllers;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.OpMode.IRobotController;

public abstract class ATeleopController implements IRobotController {
  protected Gamepad gp1, gp2;

  public ATeleopController(Gamepad gp1, Gamepad gp2) {
    this.gp1 = gp1;
    this.gp2 = gp2;
  }

  @Override
  public boolean finishedExecution() {
    return false;
  }
}
