package org.firstinspires.ftc.teamcode.OpMode.Auton.AutonStates;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.Mode;
import org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation.IMUGyro;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.DriveTrains.MecanumDriveTrain;

/**
 * <JavaDoc comment here for class>
 */
@Autonomous(name = "TestGyroTurn", group = "test")
//@Disabled
public class TestGyroTurn extends OpMode {
  // Declare OpMode fields
  private MecanumDriveTrain dt;
  private IMUGyro imu;
  private ARobotState gyroTurn;
  private final Mode mode = Mode.AUTON;


  /**
   * Code to run ONCE when the driver hits INIT
   */
  @Override
  public void init() {
    // Initialize all fields
     this.dt = new MecanumDriveTrain(hardwareMap, this.mode);
     this.imu = new IMUGyro(hardwareMap, this.mode);
     this.gyroTurn = new GyroTurn(0,0,0,180, true);

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