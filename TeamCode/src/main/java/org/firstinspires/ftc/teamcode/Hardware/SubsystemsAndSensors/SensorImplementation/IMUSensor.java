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
  @Override
  public void init(HardwareMap hwMap) {
    this.gyro = hwMap.get(BNO055IMU.class, "imu");
    Parameters parameters = new Parameters();
    parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    parameters.mode = SensorMode.GYRONLY;
    //parameters.

    this.gyro.initialize(parameters);
  }


  @Override
  public Float getOutput() {
    //TODO: TEST EXTRINSIC VS INTRINSIC

    // Z is heading axis, rotation around vector through out the top/bottom of the rev hub
    return this.gyro.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES).firstAngle;
  }
}
