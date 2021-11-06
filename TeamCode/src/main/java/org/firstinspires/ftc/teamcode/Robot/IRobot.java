package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.OpMode.ARobotState;

public interface IRobot {

  void initLoop();

  void start();

  void stop();

  void dispatchState(ARobotState robotState);

  StartParameters getParams();

  boolean isInitialized();
}
