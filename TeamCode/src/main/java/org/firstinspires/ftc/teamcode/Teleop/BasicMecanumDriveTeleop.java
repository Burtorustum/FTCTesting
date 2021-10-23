package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.RobotParameters.Mode;
import org.firstinspires.ftc.teamcode.RobotParameters.Start;
import org.firstinspires.ftc.teamcode.RobotParameters.StartParameters;
import org.firstinspires.ftc.teamcode.RobotParameters.Team;
import org.firstinspires.ftc.teamcode.StateMachine.IRobotController;
import org.firstinspires.ftc.teamcode.RobotImplementations.IRobot;
import org.firstinspires.ftc.teamcode.RobotImplementations.MecanumDriveRobot;
import org.firstinspires.ftc.teamcode.StateMachine.TeleopStateMachine;
import org.firstinspires.ftc.teamcode.Teleop.TeleopControllers.MecanumTankStrafe;

@TeleOp(name = "BasicMecanumTankTeleop", group = "Teleop")
public class BasicMecanumDriveTeleop extends OpMode {
  private IRobot robot;
  private TeleopStateMachine stateMachine;

  @Override
  public void init() {
    this.robot = new MecanumDriveRobot(hardwareMap, telemetry,
        new StartParameters(Mode.TELEOP, Start.IRRELEVANT, Team.IRRELEVANT));
    List<IRobotController> stateList = new ArrayList<>();
    stateList.add(new MecanumTankStrafe(gamepad1, gamepad2));
    //this.stateList.add(asdasd);
    // ...

    this.stateMachine = new TeleopStateMachine(this.robot, stateList);
  }

  @Override
  public void init_loop() {
    this.robot.initLoop();
  }

  @Override
  public void start() {
    this.robot.start();
  }

  @Override
  public void loop() {
    this.stateMachine.iterate();
  }

  @Override
  public void stop() {
    this.robot.stop();
  }

}