package org.firstinspires.ftc.teamcode.Robot.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.ISubsystem;

public interface ISensor {
  // double dispatch w subsystem individually for teleop and auton
  void dispatchSubsystem(ISubsystem subsystem);

  void init(HardwareMap hwMap);
}
