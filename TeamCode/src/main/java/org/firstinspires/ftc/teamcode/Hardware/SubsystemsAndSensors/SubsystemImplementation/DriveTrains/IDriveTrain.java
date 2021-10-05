package org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SubsystemImplementation.DriveTrains;

import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SubsystemImplementation.ISubsystem;

public interface IDriveTrain extends ISubsystem {

  void driveStraight(double power);

  void turnLeft(double power);

  void turnRight(double power);
}
