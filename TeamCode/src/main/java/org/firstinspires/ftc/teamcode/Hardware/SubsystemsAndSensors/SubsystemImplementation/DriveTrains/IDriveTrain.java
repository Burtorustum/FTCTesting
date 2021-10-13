package org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SubsystemImplementation.DriveTrains;

import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SensorImplementation.IOutputFunc;
import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SubsystemImplementation.ISubsystem;
import org.firstinspires.ftc.teamcode.MiniPID.MiniPID;

public interface IDriveTrain extends ISubsystem {

  void driveStraight(double power);

  void turnLeft(double power);

  void turnRight(double power);

  void turnToHeading(double basePower, int targetHeading, int tolerance, MiniPID controller, IOutputFunc<Float> curHeading);
}
