package org.firstinspires.ftc.teamcode.OpMode;

import java.util.List;

import org.firstinspires.ftc.teamcode.OpMode.StateMachine.GamepadButtons;
import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.IMUGyro;
import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.Rev2M;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

// COULD MAKE SEPARATE INTERFACE FOR EACH ROBOT ITERATION, REDUCING NUMBER OF METHODS NEEDING TO BE WRITTEN
public abstract class ARobotState {

  // In all teleop controllers this MUST return FALSE
  public abstract boolean finishedExecution();

  /**
   *
   * @return Any desired telemetry information. Each String in the list is one line of telemetry. DO NOT RETURN NULL, FOR NO TELEMETRY, RETURN EMPTY LIST
   */
  public abstract List<String> getTelemetry();

  /**
   *
   * @return a list of buttons on the gamepad used in the execution of this state.
   */
  public abstract List<GamepadButtons> getButtons();

  // NEED METHOD FOR EVERY SUBSYSTEM

  public void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain) {}

  // NEED METHOD FOR EVERY SENSOR

  public void receiveGyro(IMUGyro gyro) {}

  public void receiveRev2m(Rev2M dist) {}
}
