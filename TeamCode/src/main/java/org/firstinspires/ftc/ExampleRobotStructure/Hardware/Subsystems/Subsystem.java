package org.firstinspires.ftc.ExampleRobotStructure.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.ExampleRobotStructure.Commands.Command;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Subsystem {
    protected final Telemetry telemetry;

    public Subsystem(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public abstract void dispatch(Command command);

    public abstract void updateState();
    public abstract void updateTeleopState(Gamepad gp1, Gamepad gp2);

    public void initLoopTeleop() {}
    public void initLoopAuton() {}

    public void startTeleop() {}
    public void startAuton() {}

    public abstract void stop();
}
