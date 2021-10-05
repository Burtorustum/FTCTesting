package org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SubsystemImplementation;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Hardware.StateMachine.IRobotController;

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
