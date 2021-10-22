package org.firstinspires.ftc.teamcode.MiniPID;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

/**
 * <JavaDoc comment here for class>
 */
@Autonomous(name = "TestMiniPID", group = "AutoTest")
//@Disabled
public class TestMiniPID extends OpMode {
    // Declare OpMode fields
    private DcMotor FL, FR, BL, BR;

    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        // Initialize all fields
        this.FL = this.hardwareMap.get(DcMotor.class, "fl");
        this.FR = this.hardwareMap.get(DcMotor.class, "fr");
        this.BL = this.hardwareMap.get(DcMotor.class, "bl");
        this.BR = this.hardwareMap.get(DcMotor.class, "br");

        this.FL.setDirection(DcMotorSimple.Direction.REVERSE);
        this.FR.setDirection(DcMotorSimple.Direction.FORWARD);
        this.BL.setDirection(DcMotorSimple.Direction.REVERSE);
        this.BR.setDirection(DcMotorSimple.Direction.FORWARD);

        this.FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /**
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /**
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

    }

    /**
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }

}