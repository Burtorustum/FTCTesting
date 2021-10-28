package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;

import org.firstinspires.ftc.teamcode.Subsystems.ISubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.IMUGyro;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

public class MecanumDriveRobot extends BaseRobot {

  public MecanumDriveRobot(HardwareMap hwMap, StartParameters params) {
    super(hwMap, params);
  }

  @Override
  protected ArrayList<ISubsystem> genSubsystems(HardwareMap hwMap, StartParameters.Mode mode) {
    ArrayList<ISubsystem> subsystems = new ArrayList<>();

    subsystems.add(new IMUGyro(hwMap, mode));
    subsystems.add(new MecanumDriveTrain(hwMap, mode));

    return subsystems;
  }
}
