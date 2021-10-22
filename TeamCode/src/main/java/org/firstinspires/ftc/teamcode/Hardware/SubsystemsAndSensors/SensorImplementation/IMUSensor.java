package org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SensorImplementation;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMU.Parameters;
import com.qualcomm.hardware.bosch.BNO055IMU.SensorMode;
import com.qualcomm.hardware.bosch.BNO055IMUImpl;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class IMUSensor implements ISensor, IOutputFunc<Float>{


  private BNO055IMU gyro;

  public IMUSensor(HardwareMap hwMap) {
    this.init(hwMap);
  }

  @Override
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
// TODO: MAKE GET OUPUT TAKE AN ENUM FOR TELEMETRY OR SENSOR DATA
    // Z is heading axis, rotation around vector through out the top/bottom of the rev hub
    float x = this.gyro.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES).firstAngle;
    // Normalize values to within 0 - 359
    // want 0 heading to be at reset, -179 to be 181, -1 to be 359
    if (x < 0) {
      x = 360 + x;
    }

    return x;
  }
}
