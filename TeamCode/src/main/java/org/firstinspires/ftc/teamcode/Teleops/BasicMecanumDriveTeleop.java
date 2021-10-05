package org.firstinspires.ftc.teamcode.Teleops;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Hardware.Mode;
import org.firstinspires.ftc.teamcode.Hardware.StateMachine.IRobotController;
import org.firstinspires.ftc.teamcode.RobotImplementations.IRobot;
import org.firstinspires.ftc.teamcode.RobotImplementations.MecanumDriveRobot;
import org.firstinspires.ftc.teamcode.Teleops.States.MecanumTankStrafe;

/**
 * <JavaDoc comment here for class>
 */
@TeleOp(name = "BasicMecanumDriveTeleop", group = "Teleop")
//@Disabled
public class BasicMecanumDriveTeleop extends OpMode {
  // Declare OpMode fields
  IRobot robot;
  IRobotController controller;

  /**
   * Code to run ONCE when the driver hits INIT
   */
  @Override
  public void init() {
    // Initialize all fields
    robot = new MecanumDriveRobot(hardwareMap, telemetry, Mode.TELEOP);
    controller = new MecanumTankStrafe();
    // Tell the driver that initialization is complete.
    telemetry.addData("Status", "Initialized");
  }

  /**
   * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
   */
  @Override
  public void init_loop() {
  }

  /**
   * Code to run ONCE when the driver hits PLAY
   */
  @Override
  public void start() {

  }

  /**
   * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
   */
  @Override
  public void loop() {

  }

  /**
   * Code to run ONCE after the driver hits STOP
   */
  @Override
  public void stop() {

  }

}