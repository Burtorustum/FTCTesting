package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.Mode;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.StartParameters;
import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.IMUSensor;
import org.firstinspires.ftc.teamcode.Subsystems.ISubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

public class MecanumDriveRobot extends BaseRobot {

  public MecanumDriveRobot(HardwareMap hwMap, Telemetry telemetry, StartParameters params) {
    super(hwMap, telemetry, params);
  }

  protected ArrayList<ISubsystem> genSubsystems(HardwareMap hwMap, Mode mode) {
    ArrayList<ISubsystem> subsystems = new ArrayList<>();

    subsystems.add(new IMUSensor(hwMap));
    subsystems.add(new MecanumDriveTrain(hwMap, mode));

    return subsystems;
  }
}
