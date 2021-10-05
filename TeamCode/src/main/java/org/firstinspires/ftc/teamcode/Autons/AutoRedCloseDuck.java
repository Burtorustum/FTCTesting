package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.Hardware.Mode;
import org.firstinspires.ftc.teamcode.Hardware.StateMachine.IAutonController;
import org.firstinspires.ftc.teamcode.RobotImplementations.IRobot;
import org.firstinspires.ftc.teamcode.Hardware.StateMachine.AutonStateMachine;
import org.firstinspires.ftc.teamcode.RobotImplementations.MecanumDriveRobot;

@TeleOp(name = "AutoRedCloseDuck", group = "Auton")
//@Disabled
public class AutoRedCloseDuck extends OpMode {
  private IRobot robot;
  private List<IAutonController> stateList;
  private AutonStateMachine stateMachine;

  @Override
  public void init() {
    this.robot = new MecanumDriveRobot(hardwareMap, telemetry, Mode.AUTON);
    this.stateList = new ArrayList<IAutonController>();

    //this.stateList.add(asdasd);
    // ...

    this.stateMachine = new AutonStateMachine(this.robot, this.stateList);

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

  }

  @Override
  public void stop() {
    robot.stop();
  }

}