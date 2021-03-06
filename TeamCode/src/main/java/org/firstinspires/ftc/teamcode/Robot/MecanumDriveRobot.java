package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;
import org.firstinspires.ftc.teamcode.Robot.StartParameters.Mode;
import org.firstinspires.ftc.teamcode.Subsystems.ISubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Mechanical.DriveTrains.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.IMU.IMUGyro;

public class MecanumDriveRobot extends BaseRobot {
  private final boolean readCalibrationData;

  public MecanumDriveRobot(HardwareMap hwMap, StartParameters params, boolean readCalibrationData) {
    super(hwMap, params);
    this.readCalibrationData = readCalibrationData;
  }

  @Override
  protected ArrayList<ISubsystem> genSubsystems(HardwareMap hwMap, StartParameters.Mode mode) {
    ArrayList<ISubsystem> subsystems = new ArrayList<>();
    subsystems.add(new IMUGyro(hwMap, mode, "imu", this.readCalibrationData));
    subsystems.add(new MecanumDriveTrain(hwMap, mode));

    return subsystems;
  }
}
