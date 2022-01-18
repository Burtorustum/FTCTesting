package org.firstinspires.ftc.ExampleRobotStructure.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * <JavaDoc comment here for class>
 */
@TeleOp(name = "ExampleStateMachine", group = "")
//@Disabled
public class ExampleStateMachine extends OpMode {
    // Declare OpMode fields

    DcMotor dtl, dtr, lift, intake;

    DriveTrainState dtState;
    LiftState liftState;
    AutonState autonState;
    ElapsedTime timer;

    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        // imagine things were init here.
        this.dtState = DriveTrainState.STOP;
        this.liftState = LiftState.INTAKE;
        this.autonState = AutonState.DETECT;
        timer = new ElapsedTime();
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
        timer.reset();
    }

    private enum DriveTrainState {
        STOP, TO_DUCK, TO_DEPOSIT, TO_INTAKE, TO_PARK;
    }

    private enum LiftState {
        INTAKE, BOTTOM, MIDDLE, TOP, PRE_DEPOSIT, DEPOSIT;
    }

    private enum AutonState {
        DETECT,
        // det top -> TOP | det MID -> MIDDLE | det BOT -> BOT
        LIFTTOP, LIFTMIDDLE, LIFTBOT,
        // default -> DEPOSIT
        DEPOSIT,
        // default -> driveToDuck -> DeliverDuck
        DRIVETODUCK, DELIVERDUCK,
        // default -> DRIVETOINTAKE -> INTAKE
        DRIVETOINTAKE, INTAKE,
        // default -> DEPOSIT
        PARK,
        // default -> STOP
        STOP;
    }

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {


        switch(this.autonState) {

        }



    }

    /**
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }

}