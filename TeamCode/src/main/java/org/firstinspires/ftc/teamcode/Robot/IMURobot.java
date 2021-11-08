package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.Robot.StartParameters.Mode;
import org.firstinspires.ftc.teamcode.Subsystems.ISubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.IMU.IMUGyro;

public class IMURobot extends BaseRobot {
  private final boolean readCalibrationData;

  public IMURobot(HardwareMap hwMap, StartParameters params, boolean readCalibrationData) {
    super(hwMap, params);
    this.readCalibrationData = readCalibrationData;
  }

  @Override
  List<ISubsystem> genSubsystems(HardwareMap hwMap, Mode mode) {
    ArrayList<ISubsystem> subsystems = new ArrayList<>();
    subsystems.add(new IMUGyro(hwMap, mode, "imu", this.readCalibrationData));

    return subsystems;
  }
}
