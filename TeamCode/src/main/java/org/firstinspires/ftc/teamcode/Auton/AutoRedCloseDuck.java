package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.Auton.GyroDrive.GyroTurn;
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
    this.stateList.add(new GyroTurn(0,0,0, 180));
    //this.stateList.add(asdasd);
    // ...

    this.stateMachine = new AutonStateMachine(this.robot, this.stateList);

    this.robot.autoInit(hardwareMap);
    this.telemetry.addData("Status", "Initialized");
  }

  @Override
  public void init_loop() {
    this.robot.autoInitLoop();
  }

  @Override
  public void start() {
    this.robot.autoStart();
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