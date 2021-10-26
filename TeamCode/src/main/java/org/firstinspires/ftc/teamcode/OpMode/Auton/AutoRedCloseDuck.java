package org.firstinspires.ftc.teamcode.OpMode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.AOpMode;
import org.firstinspires.ftc.teamcode.OpMode.Auton.AutonControllers.GyroTurn;
import org.firstinspires.ftc.teamcode.OpMode.IRobotController;
import org.firstinspires.ftc.teamcode.Robot.IRobot;
import org.firstinspires.ftc.teamcode.Robot.MecanumDriveRobot;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.Mode;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.Start;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.StartParameters;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.Team;

@TeleOp(name = "AutoRedCloseDuck", group = "Auton")
//@Disabled
public class AutoRedCloseDuck extends AOpMode {

  @Override
  protected IRobot setupRobot() {
    return new MecanumDriveRobot(hardwareMap, telemetry,
        new StartParameters(Mode.AUTON, Start.CLOSEDUCK, Team.RED));
  }

  @Override
  protected List<IRobotController> setupStates() {
    List<IRobotController> stateList = new ArrayList<>();
    stateList.add(new GyroTurn(0,0,0, 180));
    //this.stateList.add(asdasd);
    // ...
    return stateList;
  }

}