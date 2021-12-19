package org.firstinspires.ftc.teamcode.Utility.DropIns;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Class of static methods for calibrating the BNO055IMU inside the REV Control Hub.
 * Place this class in any subdirectory of your TeamCode folder.
 *
 * Note: For some reason we could not get read/writing of parameter data to work properly on all
 * Control Hubs. Therefore we are going to abuse the fact that the IMU retains initialization
 * data so long as power is supplied to it. I would recommend that...
 *
 * - in Autonomous OpModes: call initGyro(hardwareMap, <configName>, true, <units>)
 * - in Teleop OpModes:     call initGyro(hardwareMap, <configName>, false, <units>)
 *
 * And create a separate opMode that's sole purpose is to calibrate the IMU. This way when you get
 * to the field for a match you can just line your robot up and init Auton, but if you want to do
 * field-centric teleop practice you can just run the calibration opMode and get straight to it.
 * Remember that if you are using field-centric drive the way you lineup and calibrate in autonomous
 * will be the "0" heading for your teleop.
 */
public class CalibrateGyro {
  /**
   * Call this in init() as {@code BNO055IMU imu = CalibrateGyro.initGyro(....);}
   *
   * @param hwMap OpMode HardwareMap instance
   * @param configName name for configuration, by default this is set as "imu"
   * @param resetHeading true if we need to reset to zero heading (typically at the beginning of autonomous),
   *                     false otherwise (typically teleops that will only be run after an autonomous mode).
   * @param units The units we want our gyro to output (either BNO055IMU.AngleUnit.DEGREES or BNO055IMU.AngleUnit.RADIANS)
   * @return An instance of your gyro, fully initialized
   */
  public static BNO055IMU initGyro(HardwareMap hwMap, String configName, boolean resetHeading, BNO055IMU.AngleUnit units) {

    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    // Enable logging
    parameters.loggingEnabled = true;
    parameters.loggingTag = "IMU";
    parameters.angleUnit = units;

    BNO055IMU gyro = hwMap.get(BNO055IMU.class, configName);
    if (resetHeading) {
      gyro.initialize(parameters);
    }

    return gyro;
  }

  /**
   * Call this in initLoop(), once per loop. Make sure to call telemetry.update()
   * at the end of your initloop method for proper telemetry output.
   *
   * @param gyro      your gyro instance
   * @param telemetry OpMode telemetry instance
   */
  public static void outputGyroInfo(BNO055IMU gyro, Telemetry telemetry) {
    // output things about the gyro in telemetry
    for (String s : getCalibrationInfo(gyro)) {
      telemetry.addLine(s);
    }
  }

  /**
   * Generates a bunch of strings representing the state of the IMU
   * @param gyro The IMU to get data from
   * @return A list of strings representing the current state of the IMU's gyro.
   */
  private static List<String> getCalibrationInfo(BNO055IMU gyro) {
    List<String> telemetryData = new ArrayList<>();
    telemetryData.add("--- Sensor: IMU Gyro ---");

    Orientation orient = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

    telemetryData.add("Z-Axis: " + orient.firstAngle);
    telemetryData.add("Y-Axis: " + orient.secondAngle);
    telemetryData.add("X-Axis: " + orient.thirdAngle);

    if (gyro.isGyroCalibrated()) {
      telemetryData.add("IMU calibrated! Press start at any time!");
    } else {
      telemetryData.add("Still calibrating. Please wait.");
    }

    return telemetryData;
  }
}
