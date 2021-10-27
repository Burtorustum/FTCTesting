package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.OpMode.ARobotState;
import org.firstinspires.ftc.teamcode.Robot.RobotParameters.StartParameters;

public interface IRobot {

    void initLoop();

    void start();

    void stop();

    void dispatchState(ARobotState robotState);

    StartParameters getParams();

    boolean isInitialized();
}
