package org.firstinspires.ftc.teamcode.OpMode;

import java.util.Collection;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.StateMachine.GamepadButtons;
import org.firstinspires.ftc.teamcode.Subsystems.Mechanical.DriveTrains.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.Color.MRColor;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.Color.RevColor;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.Distance.MRRange;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.Distance.Rev2M;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.IMU.IMUGyro;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.Switches.MRLimitSwitch;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.Switches.MRTouch;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.Switches.RevMagneticLimitSwitch;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.Switches.RevTouch;

// COULD MAKE SEPARATE ABSTRACT CLASS FOR EACH ROBOT ITERATION, REDUCING NUMBER OF METHODS
public abstract class ARobotState {

  // In all teleop controllers this MUST return FALSE
  public abstract boolean finishedExecution();

  /**
   * @return Any desired telemetry information. Each String in the list is one line of telemetry. DO
   * NOT RETURN NULL, FOR NO TELEMETRY, RETURN EMPTY LIST
   */
  public abstract Collection<String> getTelemetry();

  /**
   * @return a list of buttons on the gamepad used in the execution of this state.
   */
  public abstract Collection<GamepadButtons> getButtons();

  /**
   * TODO: Make this better, rn very annoying
   *
   * @return a list of class files that this state modifies
   */
  //public abstract List<Class<? extends ISubsystem>> getSubsystems();

  // NEED METHOD FOR EVERY MECHANICAL SUBSYSTEM ---------------------------------------------------
  public void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain) {
  }

  // NEED METHOD FOR EVERY SENSOR -----------------------------------------------------------------

  public void receiveGyro(IMUGyro gyro) {
  }

  // Distance:

  public void receiveRev2m(Rev2M dist) {
  }

  public void receiveMRRange(MRRange range) {
  }

  // Switches:

  public void receiveRevMagLimSwitch(RevMagneticLimitSwitch mag) {
  }

  public void receiveRevTouchSensor(RevTouch touch) {
  }

  public void receiveMRTouchSensor(MRTouch touch) {
  }

  public void receiveMRLimitSwitch(MRLimitSwitch limitSwitch) {
  }

  // Color:

  public void receiveRevColor(RevColor color) {
  }

  public void receiveMRColor(MRColor color) {
  }

}
