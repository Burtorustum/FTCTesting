package org.firstinspires.ftc.teamcode.Auton.AutonControllers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.RobotParameters.Mode;
import org.firstinspires.ftc.teamcode.SubsystemsAndSensors.SensorImplementation.IMUSensor;
import org.firstinspires.ftc.teamcode.SubsystemsAndSensors.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

/**
 * <JavaDoc comment here for class>
 */
@Autonomous(name = "TestGyroTurn", group = "test")
//@Disabled
public class TestGyroTurn extends OpMode {
  // Declare OpMode fields
  MecanumDriveTrain dt;
  IMUSensor imu;
  IAutonController gyroTurn;

  /**
   * Code to run ONCE when the driver hits INIT
   */
  @Override
  public void init() {
    // Initialize all fields
     this.dt = new MecanumDriveTrain(hardwareMap, Mode.AUTON);
     this.imu = new IMUSensor(hardwareMap);
     this.gyroTurn = new GyroTurn(0,0,0,180);

  }

  /**
   * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
   */
  @Override
  public void init_loop() {
    telemetry.addData("Gyro out: ",this.imu.getOutput());
  }

  /**
   * Code to run ONCE when the driver hits PLAY
   */
  @Override
  public void start() {
    this.gyroTurn.receiveGyro(this.imu);
  }

  /**
   * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
   */
  @Override
  public void loop() {
    if (!this.gyroTurn.finishedExecution()) {
      this.gyroTurn.receiveGyro(this.imu);
      this.gyroTurn.receiveMecanumDriveTrain(dt);
    }
    telemetry.addData("Gyro out: ",this.imu.getOutput());


  }

  /**
   * Code to run ONCE after the driver hits STOP
   */
  @Override
  public void stop() {

  }

}