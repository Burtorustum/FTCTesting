package org.firstinspires.ftc.teamcode.Hardware.SubsystemsAndSensors.SensorImplementation;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Hardware.StateMachine.IRobotController;

public abstract class AbstractSensor<T> implements ISensor<T>{

  @Override
  public abstract String toString();
}
