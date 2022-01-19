package org.firstinspires.ftc.ExampleRobotStructure.Hardware.Subsystems.DT;

import org.firstinspires.ftc.ExampleRobotStructure.Commands.Command;
import org.firstinspires.ftc.ExampleRobotStructure.Hardware.Subsystems.Subsystem;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class TankDriveTrain extends Subsystem {
    public TankDriveTrain(Telemetry telemetry) {
        super(telemetry);
    }

    @Override
    public void dispatch(Command command) {
        command.acceptDT(this);
    }

    public abstract void setTarget(int target);

    public abstract void moveToTarget();

    public abstract boolean arrived();

    public abstract void driveLeft(double pow);

    public abstract void driveRight(double pow);

    public abstract void drive(double pow);

    public abstract void turnClockwise(double pow);
}

