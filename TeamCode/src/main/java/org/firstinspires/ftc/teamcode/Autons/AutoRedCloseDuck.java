package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Robot.IRobot;
import org.firstinspires.ftc.teamcode.Robot.Start;
import org.firstinspires.ftc.teamcode.Robot.StateMachine.AutonStateMachine;
import org.firstinspires.ftc.teamcode.Robot.Team;
import org.firstinspires.ftc.teamcode.RobotImplementations.FirstIterationRobot;

@TeleOp(name = "AutoRedCloseDuck", group = "Auton")
//@Disabled
public class AutoRedCloseDuck extends OpMode {
  private IRobot robot;
  private AutonStateMachine stateMachine;

  @Override
  public void init() {
    this.robot = new FirstIterationRobot(hardwareMap);

    robot.autoInit(hardwareMap);
    telemetry.addData("Status", "Initialized");
  }

  @Override
  public void init_loop() {
    robot.autoInitLoop();
  }

  @Override
  public void start() {
    robot.autoStart();
  }

  @Override
  public void loop() {
    robot.runAuto(Team.RED, Start.CLOSEDUCK);
  }

  @Override
  public void stop() {
    robot.stop();
  }

}