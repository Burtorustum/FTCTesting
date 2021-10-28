package org.firstinspires.ftc.teamcode.OpMode.Auton.AutonStates;

import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.OpMode.StateMachine.GamepadButtons;

import java.util.ArrayList;
import java.util.List;

public abstract class AAutonState extends ARobotState {
    @Override
    public boolean finishedExecution() {
        return false;
    }

    @Override
    public List<String> getTelemetry() {
        return null;
    }

    @Override
    public List<GamepadButtons> getButtons() {
        return new ArrayList<>(); // Auton uses no gamepad input
    }


}
