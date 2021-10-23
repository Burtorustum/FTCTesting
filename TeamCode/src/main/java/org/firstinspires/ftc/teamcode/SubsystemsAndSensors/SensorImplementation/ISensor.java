package org.firstinspires.ftc.teamcode.SubsystemsAndSensors.SensorImplementation;

import org.firstinspires.ftc.teamcode.SubsystemsAndSensors.ISubsystem;

public interface ISensor<T> extends ISubsystem {

  T getOutput();

  @Override
  String toString();
}
