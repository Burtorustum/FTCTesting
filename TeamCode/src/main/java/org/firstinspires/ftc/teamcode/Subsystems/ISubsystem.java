package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.OpMode.ARobotState;

public interface ISubsystem {

  void autoInitLoop();

  void teleopInitLoop();

  void autoStart();

  void teleopStart();

  void stop();

  void dispatchState(ARobotState robotState);

  boolean isInitialized();

}
