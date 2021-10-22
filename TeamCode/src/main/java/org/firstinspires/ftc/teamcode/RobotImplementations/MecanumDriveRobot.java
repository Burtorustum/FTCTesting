package org.firstinspires.ftc.teamcode.RobotImplementations;

import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.Mode;
import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SensorImplementation.IMUSensor;
import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SensorImplementation.ISensor;
import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SubsystemImplementation.ISubsystem;
import org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

public class MecanumDriveRobot extends BaseRobot {

  public MecanumDriveRobot(HardwareMap hwMap, Telemetry telemetry, Mode mode) {
    super(hwMap, telemetry, mode);
  }

  protected ArrayList<ISensor> genSensors(HardwareMap hwMap, Mode mode) {
    ArrayList<ISensor> sensors = new ArrayList<>();

    sensors.add(new IMUSensor(hwMap));

    return sensors;
  }

  protected ArrayList<ISubsystem> genSubsystems(HardwareMap hwMap, Mode mode) {
    ArrayList<ISubsystem> subsystems = new ArrayList<>();

    subsystems.add(new MecanumDriveTrain(hwMap, mode));

    return subsystems;
  }
}
