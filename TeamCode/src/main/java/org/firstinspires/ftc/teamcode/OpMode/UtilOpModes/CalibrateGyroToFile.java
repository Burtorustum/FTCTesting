package org.firstinspires.ftc.teamcode.OpMode.UtilOpModes;

import com.sun.tools.javac.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.firstinspires.ftc.teamcode.OpMode.AOpMode;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.OpMode.UtilOpModes.UtilStates.GyroCalibrationTelemetryState;
import org.firstinspires.ftc.teamcode.Robot.IMURobot;
import org.firstinspires.ftc.teamcode.Robot.IRobot;
import org.firstinspires.ftc.teamcode.Robot.StartParameters;
import org.firstinspires.ftc.teamcode.Robot.StartParameters.Mode;
import org.firstinspires.ftc.teamcode.Robot.StartParameters.Start;
import org.firstinspires.ftc.teamcode.Robot.StartParameters.Team;

public class CalibrateGyroToFile extends AOpMode {

  @Override
  protected IRobot setupRobot() {
    return new IMURobot(this.hardwareMap, new StartParameters(Mode.AUTON, Start.IRRELEVANT, Team.IRRELEVANT), false);
  }

  @Override
  protected Collection<Pair<Integer, ARobotState>> setupStates() {
    List<Pair<Integer, ARobotState>> stateList = new ArrayList<>();
    stateList.add(new Pair<>(0, new GyroCalibrationTelemetryState()));
    return stateList;  }
}
