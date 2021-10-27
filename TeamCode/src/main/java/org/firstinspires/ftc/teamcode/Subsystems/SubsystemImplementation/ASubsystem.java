package org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.Mode;
import org.firstinspires.ftc.teamcode.Subsystems.ISubsystem;

public abstract class ASubsystem implements ISubsystem {
  public ASubsystem(HardwareMap hwMap, Mode mode) {
    switch (mode) {
      case AUTON:
        this.autoInit(hwMap);
        break;
      case TELEOP:
        this.teleopInit(hwMap);
        break;
    }
  }


}