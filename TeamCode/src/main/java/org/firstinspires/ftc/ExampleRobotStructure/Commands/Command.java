package org.firstinspires.ftc.ExampleRobotStructure.Commands;

import org.firstinspires.ftc.ExampleRobotStructure.Hardware.Subsystems.DT.TankDriveTrain;
import org.firstinspires.ftc.ExampleRobotStructure.Hardware.Subsystems.DuckSpinner.DuckSpinner;
import org.firstinspires.ftc.ExampleRobotStructure.Hardware.Subsystems.Subsystem;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.List;
import java.util.Set;

// Action

public interface Command {
    Set<ControllerButtons> buttonsUsed();

    List<Subsystem> subsystemsUsed();

    boolean finished();

    void applyTelemetry(Telemetry telemetry);

    void acceptDT(TankDriveTrain dt);

    void acceptDuckSpinner(DuckSpinner duckSpinner);

}