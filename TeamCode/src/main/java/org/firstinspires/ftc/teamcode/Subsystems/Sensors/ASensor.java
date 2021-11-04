package org.firstinspires.ftc.teamcode.Subsystems.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Robot.StartParameters;

public abstract class ASensor<T> implements ISensor<T> {

  public ASensor(HardwareMap hwMap, StartParameters.Mode mode, String configName) {
    switch (mode) {
      case AUTON:
        this.autoInit(hwMap, configName);
        break;
      case TELEOP:
        this.teleopInit(hwMap, configName);
        break;
    }
  }


  protected abstract void autoInit(HardwareMap hwMap, String configName);

  protected abstract void teleopInit(HardwareMap hwMap, String configName);

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
