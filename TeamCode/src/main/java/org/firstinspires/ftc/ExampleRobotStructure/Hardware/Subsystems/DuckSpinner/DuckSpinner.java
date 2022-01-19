package org.firstinspires.ftc.ExampleRobotStructure.Hardware.Subsystems.DuckSpinner;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.ExampleRobotStructure.Commands.Command;
import org.firstinspires.ftc.ExampleRobotStructure.Hardware.Subsystems.Subsystem;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DuckSpinner extends Subsystem {

    private final DcMotor spin;
    private DuckSpinState state;

    public DuckSpinner(HardwareMap hwMap, Telemetry telemetry) {
        super(telemetry);

        spin = hwMap.get(DcMotor.class, "duck");
        spin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spin.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.state = DuckSpinState.STOP;
    }

    @Override
    public void dispatch(Command command) {
        command.acceptDuckSpinner(this);
    }

    @Override
    public void updateState() {
        this.spin.setPower(this.state.speed);
    }

    @Override
    public void updateTeleopState(Gamepad gp1, Gamepad gp2) {
        if (gp1.a) {
            this.state = DuckSpinState.SPIN;
        } else if (gp1.b) {
            this.state = DuckSpinState.SPINREV;
        } else {
            this.state = DuckSpinState.STOP;
        }

        this.updateState();
    }

    public void spin() {
        this.state = DuckSpinState.SPIN;
    }

    public void spinRev() {
        this.state = DuckSpinState.SPINREV;
    }

    @Override
    public void stop() {
        this.state = DuckSpinState.STOP;
    }
}
