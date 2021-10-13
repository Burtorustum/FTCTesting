package org.firstinspires.ftc.teamcode.RobotImplementations;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.StateMachine.IRobotController;

public interface IRobot {

    void autoInit(HardwareMap hwMap);

    void teleopInit(HardwareMap hwMap);

    void autoInitLoop();

    void teleopInitLoop();

    void autoStart();

    void teleopStart();

    // replaced with auto state machine object
    // void runAuto(Team team, Start start);

    void stop();

    void dispatchState(IRobotController robotState);

    void sendTelemetry(String msg, Object... data);

    void updateTelemetry();
}
