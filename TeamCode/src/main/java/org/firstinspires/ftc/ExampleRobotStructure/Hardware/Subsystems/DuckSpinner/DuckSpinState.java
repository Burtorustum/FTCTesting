package org.firstinspires.ftc.ExampleRobotStructure.Hardware.Subsystems.DuckSpinner;

public enum DuckSpinState {
    STOP(0), SPIN(.6), SPINREV(-.6);

    public double speed;
    DuckSpinState(double speed) {
        this.speed = speed;
    }
}
