package org.firstinspires.ftc.teamcode.Teleop.TeleopControllers;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.SubsystemsAndSensors.SensorImplementation.IMUSensor;
import org.firstinspires.ftc.teamcode.SubsystemsAndSensors.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

public class MecanumTankStrafe extends AbstractTeleopController {

  public MecanumTankStrafe(Gamepad gp1, Gamepad gp2) {
    super(gp1, gp2);
  }

  @Override
  public void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain) {
    driveTrain.tankDrive(gp1.left_stick_y, gp1.right_stick_y);
  }

  @Override
  public void receiveGyro(IMUSensor gyro) {

  }


}
