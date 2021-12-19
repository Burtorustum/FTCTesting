package org.firstinspires.ftc.teamcode.Utility.DropIns;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Class to calibrate IMU and do nothing else!
 */
@Autonomous(name = "Calibrate IMU", group = "Calibration")
public class CalibrateGyroOpMode extends OpMode {
    BNO055IMU imu;

    @Override
    public void init() {
        imu = CalibrateGyro.initGyro(hardwareMap, "imu", true, BNO055IMU.AngleUnit.DEGREES);
    }

    @Override
    public void init_loop() {
        CalibrateGyro.outputGyroInfo(imu, telemetry);
        telemetry.update();
    }

    @Override
    public void loop() {
        stop();
    }
}
