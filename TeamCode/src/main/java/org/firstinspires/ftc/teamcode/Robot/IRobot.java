package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.OpMode.IRobotController;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.StartParameters;

public interface IRobot {

    void initLoop();

    void start();

    void stop();

    void dispatchState(IRobotController robotState);

    void sendTelemetry(String msg, Object... data);

    void updateTelemetry();

    StartParameters getParams();
}
