package org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SubsystemImplementation;

import com.qualcomm.robotcore.hardware.Gamepad;
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

  void autoDispatchState(IRobotController robotState);

  void teleopDispatchState(IRobotController robotState, Gamepad gp1, Gamepad gp2);

}
