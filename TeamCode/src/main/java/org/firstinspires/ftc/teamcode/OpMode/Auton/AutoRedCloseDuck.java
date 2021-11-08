package org.firstinspires.ftc.teamcode.OpMode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.sun.tools.javac.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.AOpMode;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.OpMode.Auton.AutonStates.GyroTurn;
import org.firstinspires.ftc.teamcode.Robot.IRobot;
import org.firstinspires.ftc.teamcode.Robot.MecanumDriveRobot;
import org.firstinspires.ftc.teamcode.Robot.StartParameters;

@Autonomous(name = "AutoRedCloseDuck", group = "Auton")
//@Disabled
public class AutoRedCloseDuck extends AOpMode {

  @Override
  protected IRobot setupRobot() {
    return new MecanumDriveRobot(hardwareMap,
        new StartParameters(StartParameters.Mode.AUTON, StartParameters.Start.CLOSEDUCK,
            StartParameters.Team.RED), false);

  }

  @Override
  protected Collection<Pair<Integer, ARobotState>> setupStates() {
    List<Pair<Integer, ARobotState>> stateList = new ArrayList<>();
    stateList.add(new Pair<>(0, new GyroTurn(0, 0, 0, 180, .05, true)));
    //this.stateList.add(asdasd);
    // ...
    return stateList;
  }

}