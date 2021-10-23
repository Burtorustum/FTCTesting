package org.firstinspires.ftc.teamcode.RobotImplementations;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.StateMachine.IRobotController;

public interface IRobot {

    void initLoop();

    void start();

    void stop();

    void dispatchState(IRobotController robotState);

    void sendTelemetry(String msg, Object... data);

    void updateTelemetry();
}
