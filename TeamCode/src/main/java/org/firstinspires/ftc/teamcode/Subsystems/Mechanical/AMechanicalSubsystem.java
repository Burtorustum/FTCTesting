package org.firstinspires.ftc.teamcode.Subsystems.Mechanical;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Robot.StartParameters;
import org.firstinspires.ftc.teamcode.Subsystems.ISubsystem;

public abstract class AMechanicalSubsystem implements ISubsystem {
  public AMechanicalSubsystem(HardwareMap hwMap, StartParameters.Mode mode) {
    switch (mode) {
      case AUTON:
        this.autoInit(hwMap);
        break;
      case TELEOP:
        this.teleopInit(hwMap);
        break;
    }
  }

  protected abstract void autoInit(HardwareMap hwMap);

  protected abstract void teleopInit(HardwareMap hwMap);

  @Override
  public void autoInitLoop() {}

  @Override
  public void teleopInitLoop() {}

  @Override
  public void autoStart() {}

  @Override
  public void teleopStart() {}

  @Override
  public void stop() {}
}
