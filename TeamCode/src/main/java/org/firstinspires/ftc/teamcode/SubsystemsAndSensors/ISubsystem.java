package org.firstinspires.ftc.teamcode.SubsystemsAndSensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.StateMachine.IRobotController;

public interface ISubsystem {

  void autoInit(HardwareMap hwMap);

  void teleopInit(HardwareMap hwMap);

  void autoInitLoop();

  void teleopInitLoop();

  void autoStart();

  void teleopStart();

  void stop();

  void dispatchState(IRobotController robotState);

}
