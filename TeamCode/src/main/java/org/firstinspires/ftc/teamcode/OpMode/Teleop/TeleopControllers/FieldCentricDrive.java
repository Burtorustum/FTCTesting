package org.firstinspires.ftc.teamcode.OpMode.Teleop.TeleopControllers;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.IMUSensor;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

public class FieldCentricDrive extends ATeleopController {
  private double curHeading;

  public FieldCentricDrive(Gamepad gp1, Gamepad gp2) {
    super(gp1, gp2);

    this.curHeading = 0;
  }

  @Override
  public void receiveMecanumDriveTrain(MecanumDriveTrain driveTrain) {
    driveTrain.fieldCentricDrive(gp1.left_stick_y, gp1.left_stick_x, gp1.right_stick_x, this.curHeading);
  }

  @Override
  public void receiveGyro(IMUSensor gyro) {
    this.curHeading = gyro.getOutput();
  }


}
