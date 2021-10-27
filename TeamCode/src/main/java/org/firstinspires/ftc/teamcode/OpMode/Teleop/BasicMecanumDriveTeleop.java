package org.firstinspires.ftc.teamcode.OpMode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.AOpMode;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.OpMode.Teleop.TeleopStates.MecanumTankStrafe;
import org.firstinspires.ftc.teamcode.Robot.IRobot;
import org.firstinspires.ftc.teamcode.Robot.MecanumDriveRobot;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.Mode;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.Start;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.StartParameters;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.Team;

@TeleOp(name = "BasicMecanumTankTeleop", group = "Teleop")
public class BasicMecanumDriveTeleop extends AOpMode {

  @Override
  public IRobot setupRobot() {
    return new MecanumDriveRobot(hardwareMap,
        new StartParameters(Mode.TELEOP, Start.IRRELEVANT, Team.IRRELEVANT));
  }

  @Override
  public List<ARobotState> setupStates() {
    List<ARobotState> stateList = new ArrayList<>();
    stateList.add(new MecanumTankStrafe(gamepad1, gamepad2));
    //this.stateList.add(asdasd);
    // ...

    return stateList;
  }
}