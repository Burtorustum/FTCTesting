package org.firstinspires.ftc.teamcode.StateMachine;

import org.firstinspires.ftc.teamcode.SubsystemsAndSensors.SensorImplementation.IMUSensor;
import org.firstinspires.ftc.teamcode.SubsystemsAndSensors.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

// COULD MAKE SEPARATE INTERFACE FOR EACH ROBOT ITERATION, REDUCING NUMBER OF METHODS NEEDING TO BE WRITTEN
public interface IRobotController {

  // NEED METHOD FOR EVERY SUBSYSTEM

  void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain);

  // NEED METHOD FOR EVERY SENSOR

  void receiveGyro(IMUSensor gyro);
}
