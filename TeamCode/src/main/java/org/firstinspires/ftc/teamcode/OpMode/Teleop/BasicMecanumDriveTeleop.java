package org.firstinspires.ftc.teamcode.OpMode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sun.tools.javac.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.AOpMode;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.OpMode.Teleop.TeleopStates.FourMotorTankDrive;
import org.firstinspires.ftc.teamcode.Robot.IRobot;
import org.firstinspires.ftc.teamcode.Robot.MecanumDriveRobot;
import org.firstinspires.ftc.teamcode.Robot.StartParameters;

@TeleOp(name = "BasicMecanumTankTeleop", group = "Teleop")
public class BasicMecanumDriveTeleop extends AOpMode {

  @Override
  public IRobot setupRobot() {
    return new MecanumDriveRobot(hardwareMap,
        new StartParameters(StartParameters.Mode.TELEOP, StartParameters.Start.IRRELEVANT,
            StartParameters.Team.IRRELEVANT), true);
  }

  @Override
  public Collection<Pair<Integer, ARobotState>> setupStates() {
    List<Pair<Integer, ARobotState>> stateList = new ArrayList<>();
    stateList.add(new Pair<>(0, new FourMotorTankDrive(gamepad1, gamepad2)));
    //this.stateList.add(asdasd);
    // ...

    return stateList;
  }
}