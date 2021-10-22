package org.firstinspires.ftc.teamcode.Hardware.StateMachine;

import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SensorImplementation.IMUSensor;
import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

// COULD MAKE SEPARATE INTERFACE FOR EACH ROBOT ITERATION, REDUCING NUMBER OF METHODS NEEDING TO BE WRITTEN
public interface IRobotController {

  // NEED METHOD FOR EVERY SUBSYSTEM

  void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain);

  // NEED METHOD FOR EVERY SENSOR

  void receiveGyro(IMUSensor gyro);
}
