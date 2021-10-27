package org.firstinspires.ftc.teamcode.Subsystems.SensorImplementation;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMU.Parameters;
import com.qualcomm.hardware.bosch.BNO055IMU.SensorMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.Mode;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemImplementation.ASubsystem;

public class IMUGyro extends ASubsystem implements ISensor<Float> {

  private BNO055IMU gyro;

  public IMUGyro(HardwareMap hwMap, Mode mode) {
    super(hwMap, mode);
  }

  /**
   *
   * @return Heading between 0 and 359 degrees
   */
  @Override
  public Float getOutput() {
    // Z is heading axis, rotation around vector through out the top/bottom of the rev hub
    float x = this.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES).firstAngle;
    // Normalize values to within 0 - 359
    // want 0 heading to be at reset, -179 to be 181, -1 to be 359
    if (x < 0) {
      x = 360 + x;
    }

    return x;
  }

  @Override
  public void autoInit(HardwareMap hwMap) {
    this.gyro = hwMap.get(BNO055IMU.class, "imu");

    Parameters parameters = new Parameters();
    parameters.calibrationDataFile = "BNO055IMUCalibration.json";
    parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    parameters.mode = SensorMode.GYRONLY;

    this.gyro.initialize(parameters);
  }

  @Override
  public void teleopInit(HardwareMap hwMap) {
    this.gyro = hwMap.get(BNO055IMU.class, "imu");

    Parameters parameters = new Parameters();
    parameters.calibrationDataFile = "BNO055IMUCalibration.json";
    parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    parameters.mode = SensorMode.GYRONLY;

    // Ideally don't need to re-calibrate bc should be calibrated from auton
    //this.gyro.initialize(parameters);
  }

  @Override
  public void autoInitLoop() {

  }

  @Override
  public void teleopInitLoop() {

  }

  @Override
  public void autoStart() {

  }

  @Override
  public void teleopStart() {

  }

  @Override
  public void stop() {

  }

  @Override
  public void dispatchState(ARobotState robotState) {
    robotState.receiveGyro(this);
  }

  @Override
  public boolean isInitialized() {
    return this.gyro.isGyroCalibrated();
  }

}
