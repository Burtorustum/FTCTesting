package org.firstinspires.ftc.teamcode.SubsystemsAndSensors.SensorImplementation;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMU.Parameters;
import com.qualcomm.hardware.bosch.BNO055IMU.SensorMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.StateMachine.IRobotController;

public class IMUSensor extends AbstractSensor<Float>{

  private BNO055IMU gyro;

  public IMUSensor(HardwareMap hwMap) {
    this.init(hwMap);
  }

  //TODO: split to other inits
  public void init(HardwareMap hwMap) {
    this.gyro = hwMap.get(BNO055IMU.class, "imu");
    Parameters parameters = new Parameters();
    parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    parameters.mode = SensorMode.GYRONLY;
    //parameters.

    this.gyro.initialize(parameters);
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

  }

  @Override
  public void teleopInit(HardwareMap hwMap) {

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
  public void dispatchState(IRobotController robotState) {
    robotState.receiveGyro(this);
  }

  @Override
  public String toString() {
    return "Heading: " + this.getOutput();
  }
}
