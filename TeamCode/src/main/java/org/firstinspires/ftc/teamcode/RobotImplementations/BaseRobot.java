package org.firstinspires.ftc.teamcode.RobotImplementations;

import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.Arrays;
import java.util.List;
import org.firstinspires.ftc.teamcode.Robot.IRobot;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.ISubsystem;

public abstract class BaseRobot implements IRobot {
  private HardwareMap hwMap;
  private List<ISubsystem> subsystemList;

  public BaseRobot(HardwareMap hwMap, ISubsystem ...subsystems) {
    this.hwMap = hwMap;
    this.subsystemList = Arrays.asList(subsystems);
  }

}