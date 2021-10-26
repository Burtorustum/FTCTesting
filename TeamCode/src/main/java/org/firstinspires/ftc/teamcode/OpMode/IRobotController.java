package org.firstinspires.ftc.teamcode.OpMode;

import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.IMUSensor;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

// COULD MAKE SEPARATE INTERFACE FOR EACH ROBOT ITERATION, REDUCING NUMBER OF METHODS NEEDING TO BE WRITTEN
public interface IRobotController {

  // In all teleop controllers this will return FALSE
  boolean finishedExecution();

  // NEED METHOD FOR EVERY SUBSYSTEM

  void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain);

  // NEED METHOD FOR EVERY SENSOR

  void receiveGyro(IMUSensor gyro);
}
