package org.firstinspires.ftc.ExampleRobotStructure.Hardware;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.ExampleRobotStructure.Commands.Command;
import org.firstinspires.ftc.ExampleRobotStructure.Hardware.Subsystems.Subsystem;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.List;

public abstract class BaseRobot {
    private final Telemetry telemetry;
    private final List<Subsystem> subsystems;
    private final boolean autonomous;

    public BaseRobot(HardwareMap hwMap, Telemetry telemetry, boolean autonomous) {
        this.telemetry = telemetry;

        subsystems = this.generateSubsystems(hwMap, autonomous);
        this.autonomous = autonomous;
    }

    public abstract List<Subsystem> generateSubsystems(HardwareMap hwMap, boolean autonomous);

    public void initLoop() {
        for (Subsystem s : subsystems) {
            if (autonomous) {
                s.initLoopAuton();
            } else {
                s.initLoopTeleop();
            }
        }
    }

    public void start() {
        for (Subsystem s : subsystems) {
            if (autonomous) {
                s.startAuton();
            } else {
                s.startTeleop();
            }
        }
    }

    public void dispatch(Command command) {
        for (Subsystem s : subsystems) {
            s.dispatch(command);
        }
    }

    public void updateTeleopState(Gamepad gp1, Gamepad gp2) {
        for (Subsystem s : subsystems) {
            s.updateTeleopState(gp1, gp2);
        }
    }

    public void updateAutonomousState(boolean redSide, boolean nearDuckStart) {
        if (redSide && nearDuckStart) {
            redDuckUpdateState();
        } else if (redSide) {
            redCargoUpdateState();
        } else if (nearDuckStart) {
            blueDuckUpdateState();
        } else {
            blueCargoUpdateState();
        }
    }

    protected abstract void blueDuckUpdateState();
    protected abstract void blueCargoUpdateState();
    protected abstract void redDuckUpdateState();
    protected abstract void redCargoUpdateState();

    public void stop() {
        for (Subsystem s : subsystems) {
            s.stop();
        }
    }

}
