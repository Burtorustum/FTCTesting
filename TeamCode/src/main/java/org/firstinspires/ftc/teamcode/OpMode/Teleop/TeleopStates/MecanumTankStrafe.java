package org.firstinspires.ftc.teamcode.OpMode.Teleop.TeleopStates;

import com.qualcomm.robotcore.hardware.Gamepad;
import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.IMUGyro;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

public class MecanumTankStrafe extends ATeleopState {

  public MecanumTankStrafe(Gamepad gp1, Gamepad gp2) {
    super(gp1, gp2);
  }

  @Override
  public List<String> getTelemetry() {
    return new ArrayList<>();
  }

  @Override
  public void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain) {
    driveTrain.tankDrive(gp1.left_stick_y, gp1.right_stick_y);
  }

  @Override
  public void receiveGyro(IMUGyro gyro) {

  }


}
