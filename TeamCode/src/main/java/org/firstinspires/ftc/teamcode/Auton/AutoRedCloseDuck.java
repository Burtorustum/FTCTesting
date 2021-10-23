package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.Auton.AutonControllers.GyroTurn;
import org.firstinspires.ftc.teamcode.RobotParameters.Mode;
import org.firstinspires.ftc.teamcode.RobotParameters.Start;
import org.firstinspires.ftc.teamcode.RobotParameters.StartParameters;
import org.firstinspires.ftc.teamcode.RobotParameters.Team;
import org.firstinspires.ftc.teamcode.Auton.AutonControllers.IAutonController;
import org.firstinspires.ftc.teamcode.RobotImplementations.IRobot;
import org.firstinspires.ftc.teamcode.StateMachine.AutonStateMachine;
import org.firstinspires.ftc.teamcode.RobotImplementations.MecanumDriveRobot;

@TeleOp(name = "AutoRedCloseDuck", group = "Auton")
//@Disabled
public class AutoRedCloseDuck extends OpMode {
  private IRobot robot;
  private AutonStateMachine stateMachine;

  @Override
  public void init() {
    this.robot = new MecanumDriveRobot(hardwareMap, telemetry,
        new StartParameters(Mode.AUTON, Start.CLOSEDUCK, Team.RED));
    List<IAutonController> stateList = new ArrayList<>();
    stateList.add(new GyroTurn(0,0,0, 180));
    //this.stateList.add(asdasd);
    // ...

    this.stateMachine = new AutonStateMachine(this.robot, stateList);
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