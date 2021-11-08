package org.firstinspires.ftc.teamcode.Subsystems.Sensors.IMU;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMU.Parameters;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ReadWriteFile;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.StartParameters;
import org.firstinspires.ftc.teamcode.Subsystems.Sensors.ASensor;

public class IMUGyro extends ASensor<Float> {

  private BNO055IMU gyro;

  private final boolean readCalibrationData;
  private final String filename = "BNO055IMUCalibration.json";
  private boolean calibrationComplete = false;

  public IMUGyro(HardwareMap hwMap, StartParameters.Mode mode, String configName, boolean readCalibrationData) {
    super(hwMap, mode, configName);
    this.readCalibrationData = readCalibrationData;
  }

  /**
   * @return Heading as 0<= x < 360 degrees
   */
  @Override
  public Float getOutput() {
    // Z is heading axis, rotation around vector through out the top/bottom of the rev hub
    float x = this.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY,
        AngleUnit.DEGREES).firstAngle;
    // Set values to 0 <= x < 360
    // want 0 heading to be at reset, -179 to be 181, -1 to be 359
    if (x < 0) {
      x = 360 + x;
    }

    return x;
  }

  @Override
  public void autoInit(HardwareMap hwMap, String configName) {
    this.generalInit(hwMap, configName);
  }

  @Override
  public void teleopInit(HardwareMap hwMap, String configName) {
    this.generalInit(hwMap, configName);
  }

  private void generalInit(HardwareMap hwMap, String configName) {
    this.gyro = hwMap.get(BNO055IMU.class, configName);

    Parameters parameters = new BNO055IMU.Parameters();
    parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;

    // If read calibration data from file, set param to filename
    if (this.readCalibrationData) {
      parameters.calibrationDataFile = this.filename;
    }
    // Otherwise log calibration and do not read data from file
    else {
      parameters.calibrationDataFile = null;
      parameters.loggingEnabled = true;
      parameters.loggingTag = "IMU";
    }
    // Initialize gyro with either data from file or new data
    this.gyro.initialize(parameters);

    // If we aren't reading from a file, write new data to file
    if (!this.readCalibrationData) {
      this.writeCalibrationDataToFile();
    }

    // Calibration done
    this.calibrationComplete = true;
  }

  private void writeCalibrationDataToFile() {
    BNO055IMU.CalibrationData calibrationData = this.gyro.readCalibrationData();
    File file = AppUtil.getInstance().getSettingsFile(filename);
    ReadWriteFile.writeFile(file, calibrationData.serialize());
  }

  @Override
  public void autoInitLoop() {
    if (!this.calibrationComplete && this.gyro.isGyroCalibrated()) {
      BNO055IMU.CalibrationData calibrationData = this.gyro.readCalibrationData();
      File file = AppUtil.getInstance().getSettingsFile(this.filename);
      ReadWriteFile.writeFile(file, calibrationData.serialize());
      this.calibrationComplete = true;
    }
  }

  @Override
  public void dispatchState(ARobotState robotState) {
    robotState.receiveGyro(this);
  }

  @Override
  public boolean isInitialized() {
    return this.calibrationComplete;
  }

  public List<String> getCalibrationInfo() {
    List<String> telemetryData = new ArrayList<>();
    telemetryData.add("Status: " + this.gyro.getSystemStatus().toShortString());
    telemetryData.add("Calib Status: " + this.gyro.getCalibrationStatus().toString());
    telemetryData.add("Gyro Calib? " + this.gyro.isGyroCalibrated());
    telemetryData.add("heading: " + this.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);

    return telemetryData;
  }

}
