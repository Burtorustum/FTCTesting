package org.firstinspires.ftc.teamcode.Teleop.TeleopControllers;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.StateMachine.IRobotController;

public abstract class AbstractTeleopController implements IRobotController {
  protected Gamepad gp1, gp2;

  public AbstractTeleopController(Gamepad gp1, Gamepad gp2) {
    this.gp1 = gp1;
    this.gp2 = gp2;
  }
}
